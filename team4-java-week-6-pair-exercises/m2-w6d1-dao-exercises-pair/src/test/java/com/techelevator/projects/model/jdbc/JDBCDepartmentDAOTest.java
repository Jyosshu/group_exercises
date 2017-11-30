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

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;

public class JDBCDepartmentDAOTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCDepartmentDAO dao;
	private JdbcTemplate jdbcTemplate;
	private Long dotNetId;
	
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
		jdbcTemplate.update("DELETE FROM department");
		
		dao = new JDBCDepartmentDAO(dataSource);
		jdbcTemplate.update("INSERT INTO department (name) VALUES ('JAVA')");
		jdbcTemplate.update("INSERT INTO department (name) VALUES ('GUAVA')");
		dotNetId = jdbcTemplate.queryForObject("INSERT INTO department (name) VALUES ('DOTNET') RETURNING department_id", Long.class);
		
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public void testGetAllDepartments() {
		List<Department> sut = dao.getAllDepartments();
		String sqlGetAllDept = "SELECT * FROM department ORDER BY name";
		
		SqlRowSet departmentRowSet = jdbcTemplate.queryForRowSet(sqlGetAllDept);
		
		assertNotNull(sut);
		departmentRowSet.next();
		assertEquals(sut.get(0).getName(), departmentRowSet.getString("name"));
		assertEquals(sut.get(0).getId(), (Long)departmentRowSet.getLong("department_id"));
		departmentRowSet.next();
		assertEquals(sut.get(1).getName(), departmentRowSet.getString("name"));
		assertEquals(sut.get(1).getId(), (Long)departmentRowSet.getLong("department_id"));
		assertEquals(sut.size(), 3);
	}

	@Test
	public void testSearchDepartmentsByName() {		
		List<Department> sut = dao.searchDepartmentsByName("AV");
		String sqlSelectDepartment = "SELECT * FROM department WHERE name LIKE ? ORDER BY name";
		
		SqlRowSet departmentRowSet = jdbcTemplate.queryForRowSet(sqlSelectDepartment, "%AV%");
		
		
		assertNotNull (sut);
		departmentRowSet.next();
		assertEquals(sut.get(0).getName(), departmentRowSet.getString("name"));
		assertEquals(sut.get(0).getId(), (Long)departmentRowSet.getLong("department_id"));
		departmentRowSet.next();
		assertEquals(sut.get(1).getName(), departmentRowSet.getString("name"));
		assertEquals(sut.get(1).getId(), (Long)departmentRowSet.getLong("department_id"));
		assertEquals(sut.size(), 2);
	}

	@Test
	public void testUpdateDepartmentName() {
		dao.updateDepartmentName(dotNetId, "C#");
		String sqlSelectDepartment = "SELECT name FROM department WHERE department_id = ?";
		String nameUpdate = jdbcTemplate.queryForObject(sqlSelectDepartment, String.class, dotNetId);
		
		assertNotNull(nameUpdate);
		assertEquals("C#", nameUpdate);
	}

	@Test
	public void testCreateDepartment() {
		String deptName = "MY NEW TEST DEPT";
		Department newDept = dao.createDepartment(deptName);
		
		assertNotNull (newDept);
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM department WHERE department_id=?", newDept.getId());
		assertTrue("There were no departments in the database", results.next());
		assertEquals(deptName, results.getString("name"));
		assertEquals(newDept.getId(), (Long)results.getLong("department_id"));
		assertFalse("Too many rows", results.next());
	}

	@Test
	public void testGetDepartmentById() {
		String sqlSearchDepartment = "SELECT name FROM department WHERE department_id = ?";
		String deptName = jdbcTemplate.queryForObject(sqlSearchDepartment, String.class, dotNetId);
		dao.getDepartmentById(dotNetId);
		
		assertEquals(deptName, dao.getDepartmentById(dotNetId).getName());
	}

}
