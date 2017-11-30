package com.techelevator.projects.model.jdbc;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.jdbc.JDBCProjectDAO;;

public class JDBCProjectDAOTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCProjectDAO dao;
	private JdbcTemplate jdbcTemplate;
	private Long[] projectIds;
	private Long peachId;
	private Long toadId;
	
	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections 
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}
	
	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM project_employee");
		jdbcTemplate.update("DELETE FROM employee");
		jdbcTemplate.update("DELETE FROM project");
		
		dao = new JDBCProjectDAO(dataSource);
		
		projectIds=new Long[5];
		
		projectIds[0]=jdbcTemplate.queryForObject("INSERT INTO project (name, to_date) VALUES ('Blue Shell', '01/01/2015') RETURNING project_id", Long.class); 
		projectIds[1]=jdbcTemplate.queryForObject("INSERT INTO project (name, from_date, to_date) VALUES ('Red Shell', '01/01/2015', '01/01/10000') RETURNING project_id", Long.class); //active
		projectIds[2]=jdbcTemplate.queryForObject("INSERT INTO project (name) VALUES ('Banana') RETURNING project_id", Long.class); 
		projectIds[3]=jdbcTemplate.queryForObject("INSERT INTO project (name, from_date) VALUES ('Kappa', '01/01/2015') RETURNING project_id", Long.class); //active
		projectIds[4]=jdbcTemplate.queryForObject("INSERT INTO project (name, from_date) VALUES ('Star', '01/01/10000') RETURNING project_id", Long.class);
		
		peachId=jdbcTemplate.queryForObject("INSERT INTO employee (department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (2, 'PRINCESS', 'PEACH', CURRENT_DATE, 'F', CURRENT_DATE) RETURNING employee_id", Long.class);
		toadId = jdbcTemplate.queryForObject("INSERT INTO employee (department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (3, 'TOAD', 'STOOL', CURRENT_DATE, 'M', CURRENT_DATE) RETURNING employee_id", Long.class);
		jdbcTemplate.update("INSERT INTO project_employee (project_id, employee_id) VALUES (?, ?)", projectIds[2], peachId);
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	@Test
	public void testGetAllActiveProjects() {
		List<Project> p=dao.getAllActiveProjects();
		String sqlAllActiveProjects = "SELECT * FROM project WHERE (to_date IS NOT NULL AND to_date >= CURRENT_DATE) OR (from_date < CURRENT_DATE AND to_date IS NULL) ORDER BY name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllActiveProjects);
		
		assertNotNull(p);
		assertEquals(2, p.size());
		results.next();
		assertEquals(p.get(0).getId(), (Long)results.getLong("project_id"));
		results.next();
		assertEquals(p.get(1).getId(), (Long)results.getLong("project_id"));
	}
	
	@Test
	public void testRemoveEmployeeFromProject() {
		dao.removeEmployeeFromProject(projectIds[2], peachId);
		String sqlRemoveProject = "SELECT * FROM project_employee WHERE project_id =? AND employee_id =?";
		SqlRowSet result=jdbcTemplate.queryForRowSet(sqlRemoveProject, projectIds[2], peachId);
		
		
		assertFalse(result.next());
	}


	@Test
	public void testAddEmployeeToProject() {
		dao.addEmployeeToProject(projectIds[0], toadId);
		String sqlRemoveProject = "SELECT * FROM project_employee WHERE project_id =? AND employee_id =?";
		SqlRowSet result=jdbcTemplate.queryForRowSet(sqlRemoveProject, projectIds[0], toadId);
		
		assertTrue(result.next());
		assertEquals(projectIds[0], (Long)result.getLong("project_id"));
		assertEquals(toadId, (Long)result.getLong("employee_id"));
		assertFalse(result.next());
	}

}
