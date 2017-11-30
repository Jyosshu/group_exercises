package com.techelevator.projects.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> newEmployeeList = new ArrayList<>();
		String sqlSelectEmployee = "SELECT * FROM employee ORDER BY last_name, first_name";
		
		SqlRowSet employeeRowSet =jdbcTemplate.queryForRowSet(sqlSelectEmployee);
		
		while (employeeRowSet.next()) {
						
			newEmployeeList.add(mapRowToEmployee(employeeRowSet));
		}
		
		return newEmployeeList;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> newEmployeeList = new ArrayList<>();
		String sqlSearchEmployee = "SELECT * FROM employee WHERE UPPER(first_name) LIKE ? AND UPPER(last_name) LIKE ? ORDER BY last_name, first_name";
		
		SqlRowSet employeeRowSet =jdbcTemplate.queryForRowSet(sqlSearchEmployee, "%" + firstNameSearch.toUpperCase() + "%", "%" + lastNameSearch.toUpperCase() + "%");
		
		while (employeeRowSet.next()) {
			
			newEmployeeList.add(mapRowToEmployee(employeeRowSet));
		}
		
		return newEmployeeList;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		List<Employee> newEmployeeList = new ArrayList<>();
		String sqlGetEmployeeById = "SELECT * FROM employee WHERE department_id =? ORDER BY last_name, first_name";
		
		SqlRowSet employeeRowSet =jdbcTemplate.queryForRowSet(sqlGetEmployeeById, id);
		
		while (employeeRowSet.next()) {
			
			newEmployeeList.add(mapRowToEmployee(employeeRowSet));
		}
		
		return newEmployeeList;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> newEmployeeList = new ArrayList<>();
		String sqlGetEmployeeWithoutProject = "SELECT e.* FROM employee e LEFT JOIN project_employee ec ON ec.employee_id = e.employee_id WHERE ec.project_id IS NULL ORDER BY last_name, first_name";
		
		SqlRowSet employeeRowSet =jdbcTemplate.queryForRowSet(sqlGetEmployeeWithoutProject);
		
		while (employeeRowSet.next()) {
			
			newEmployeeList.add(mapRowToEmployee(employeeRowSet));
		}
		
		return newEmployeeList;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		List<Employee> newEmployeeList = new ArrayList<>();
		String sqlGetEmployeeByProjectId = "SELECT e.* FROM project_employee ec JOIN employee e ON e.employee_id = ec.employee_id WHERE ec.project_id =? ORDER BY e.last_name, e.first_name";
		
		SqlRowSet employeeRowSet = jdbcTemplate.queryForRowSet(sqlGetEmployeeByProjectId, projectId);
		
		while (employeeRowSet.next()) {
			
			newEmployeeList.add(mapRowToEmployee(employeeRowSet));
		}
		
		return newEmployeeList;
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		String sqlUpdateEmployeeDepartment = "UPDATE employee SET department_id =? WHERE employee_id =?";
		jdbcTemplate.update(sqlUpdateEmployeeDepartment, departmentId, employeeId);
	}
	
	private Employee mapRowToEmployee(SqlRowSet employeeRowSet) {
		Employee newEmployee = new Employee();
		
		long empId = employeeRowSet.getLong("employee_id");
		long deptId = employeeRowSet.getLong("department_id");
		String firstName = employeeRowSet.getString("first_name");
		String lastName = employeeRowSet.getString("last_name");
		LocalDate birthDate = employeeRowSet.getDate("birth_date").toLocalDate();
		char gender = employeeRowSet.getString("gender").toUpperCase().charAt(0);
		LocalDate hireDate = employeeRowSet.getDate("hire_date").toLocalDate();
	
		
		newEmployee.setId(empId);
		newEmployee.setDepartmentId(deptId);
		newEmployee.setFirstName(firstName);
		newEmployee.setLastName(lastName);
		newEmployee.setBirthDay(birthDate);
		newEmployee.setGender(gender);
		newEmployee.setHireDate(hireDate);
		
		return newEmployee;
	}

}
