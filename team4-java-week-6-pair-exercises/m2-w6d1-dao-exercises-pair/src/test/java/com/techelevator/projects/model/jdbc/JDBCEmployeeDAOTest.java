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

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;

public class JDBCEmployeeDAOTest {
	private static SingleConnectionDataSource dataSource;
	private JDBCEmployeeDAO dao;
	private JdbcTemplate jdbcTemplate;
	private Long toadId;
	private long peachId;
	
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
		
		dao = new JDBCEmployeeDAO(dataSource);
		jdbcTemplate.update("INSERT INTO employee (department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (1, 'MARIO', 'MARIO', CURRENT_DATE, 'M', CURRENT_DATE)");
		jdbcTemplate.update("INSERT INTO employee (department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (1, 'LUIGI', 'MARIO', CURRENT_DATE, 'M', CURRENT_DATE)");
		peachId=jdbcTemplate.queryForObject("INSERT INTO employee (department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (2, 'PRINCESS', 'PEACH', CURRENT_DATE, 'F', CURRENT_DATE) RETURNING employee_id", Long.class);
		toadId = jdbcTemplate.queryForObject("INSERT INTO employee (department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (3, 'TOAD', 'STOOL', CURRENT_DATE, 'M', CURRENT_DATE) RETURNING employee_id", Long.class);
		jdbcTemplate.update("INSERT INTO project_employee (project_id, employee_id) VALUES (2, ?)", peachId);
		jdbcTemplate.update("INSERT INTO project_employee (project_id, employee_id) VALUES (2, ?)", toadId);
	

	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	@Test
	public void testGetAllEmployees() {
		List<Employee> imps=dao.getAllEmployees();
		String sqlGrabAllEmployees="SELECT * FROM employee ORDER BY last_name, first_name";
		SqlRowSet results=jdbcTemplate.queryForRowSet(sqlGrabAllEmployees);
		
		assertNotNull(imps);
		assertEquals(4, imps.size());
		results.next();
		assertEquals(imps.get(0).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(0).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(0).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(0).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(0).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(0).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(0).getHireDate(), results.getDate("hire_date").toLocalDate());
		
		results.next();
		assertEquals(imps.get(1).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(1).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(1).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(1).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(1).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(1).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(1).getHireDate(), results.getDate("hire_date").toLocalDate());
		
		results.next();
		assertEquals(imps.get(2).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(2).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(2).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(2).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(2).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(2).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(2).getHireDate(), results.getDate("hire_date").toLocalDate());
		
		results.next();
		assertEquals(imps.get(3).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(3).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(3).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(3).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(3).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(3).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(3).getHireDate(), results.getDate("hire_date").toLocalDate());

	}

	@Test
	public void testSearchEmployeesByName() {
		List<Employee> imps=dao.searchEmployeesByName("I", "aR");
		
		String sqlCommandToGetSearch = "SELECT * FROM employee WHERE UPPER(first_name) LIKE ? AND UPPER(last_name) LIKE ? ORDER BY last_name, first_name";
		SqlRowSet results=jdbcTemplate.queryForRowSet(sqlCommandToGetSearch, "%" + "I".toUpperCase() + "%", "%" + "aR".toUpperCase() + "%");
		
		assertNotNull(imps);
		assertEquals(2, imps.size());
		results.next();
		assertEquals(imps.get(0).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(0).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(0).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(0).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(0).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(0).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(0).getHireDate(), results.getDate("hire_date").toLocalDate());
		
		results.next();
		assertEquals(imps.get(1).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(1).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(1).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(1).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(1).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(1).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(1).getHireDate(), results.getDate("hire_date").toLocalDate());


	}

	@Test
	public void testGetEmployeesByDepartmentId() {
		List<Employee> imps=dao.getEmployeesByDepartmentId(1);
		
		String sqlCommandToGetEmployeeById = "SELECT * FROM employee WHERE department_id =? ORDER BY last_name, first_name";
		SqlRowSet results=jdbcTemplate.queryForRowSet(sqlCommandToGetEmployeeById, 1);
		
		assertNotNull(imps);
		assertEquals(2, imps.size());
		results.next();
		assertEquals(imps.get(0).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(0).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(0).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(0).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(0).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(0).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(0).getHireDate(), results.getDate("hire_date").toLocalDate());
		
		results.next();
		assertEquals(imps.get(1).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(1).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(1).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(1).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(1).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(1).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(1).getHireDate(), results.getDate("hire_date").toLocalDate());
		
	}

	@Test
	public void testGetEmployeesWithoutProjects() {
		List<Employee> imps=dao.getEmployeesWithoutProjects();
		
		String sqlGetEmployeeWithoutProject = "SELECT e.* FROM employee e LEFT JOIN project_employee ec ON ec.employee_id = e.employee_id WHERE ec.project_id IS NULL ORDER BY last_name, first_name";
		SqlRowSet results=jdbcTemplate.queryForRowSet(sqlGetEmployeeWithoutProject);
		
		assertNotNull(imps);
		assertEquals(2, imps.size());
		results.next();
		assertEquals(imps.get(0).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(0).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(0).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(0).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(0).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(0).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(0).getHireDate(), results.getDate("hire_date").toLocalDate());
		
		results.next();
		assertEquals(imps.get(1).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(1).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(1).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(1).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(1).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(1).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(1).getHireDate(), results.getDate("hire_date").toLocalDate());
	}

	@Test
	public void testGetEmployeesByProjectId() {
		List<Employee> imps=dao.getEmployeesByProjectId(2L);
		
		String sqlGetEmployeeByProjectId = "SELECT e.* FROM project_employee ec JOIN employee e ON e.employee_id = ec.employee_id WHERE ec.project_id =? ORDER BY e.last_name, e.first_name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeeByProjectId, 2);
		
		assertNotNull(imps);
		assertEquals(2, imps.size());
		results.next();
		assertEquals(imps.get(0).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(0).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(0).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(0).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(0).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(0).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(0).getHireDate(), results.getDate("hire_date").toLocalDate());
		
		results.next();
		assertEquals(imps.get(1).getId(), (Long)results.getLong("employee_id"));
		assertEquals(imps.get(1).getDepartmentId(), results.getLong("department_id"));
		assertEquals(imps.get(1).getFirstName(), results.getString("first_name"));
		assertEquals(imps.get(1).getLastName(), results.getString("last_name"));
		assertEquals(imps.get(1).getGender(), results.getString("gender").charAt(0));
		assertEquals(imps.get(1).getBirthDay(), results.getDate("birth_date").toLocalDate());
		assertEquals(imps.get(1).getHireDate(), results.getDate("hire_date").toLocalDate());
	}

	@Test
	public void testChangeEmployeeDepartment() {
		String sqlCurrentEmployeeDepartment = "SELECT department_id FROM employee WHERE employee_id =?";
		SqlRowSet before = jdbcTemplate.queryForRowSet(sqlCurrentEmployeeDepartment, toadId);
		before.next();
		assertEquals(3, before.getLong("department_id"));
		
		dao.changeEmployeeDepartment(toadId, 4l);
		
	
		String sqlUpdateEmployeeDepartment = "SELECT department_id FROM employee WHERE employee_id =?";
		SqlRowSet after = jdbcTemplate.queryForRowSet(sqlUpdateEmployeeDepartment, toadId);
	
		after.next();
		assertEquals(4, after.getLong("department_id"));
		
		
		
	}

}
