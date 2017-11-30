package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> newDepartmentList = new ArrayList<>();
		String sqlSelectDepartment = "SELECT * FROM department";
		
		SqlRowSet departmentRowSet =jdbcTemplate.queryForRowSet(sqlSelectDepartment);
		
		while (departmentRowSet.next()) {
			long id = departmentRowSet.getLong("department_id");
			String name = departmentRowSet.getString("name");
			newDepartmentList.add(mapRowToDepartment(id, name));
		}
		
		return newDepartmentList;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		List<Department> newDepartmentList = new ArrayList<>();
		String sqlSelectDepartment = "SELECT * FROM department WHERE UPPER(name) LIKE ? ORDER BY name";
		
		SqlRowSet departmentRowSet =jdbcTemplate.queryForRowSet(sqlSelectDepartment, "%" + nameSearch.toUpperCase() + "%");
		
		while (departmentRowSet.next()) {
			long id = departmentRowSet.getLong("department_id");
			String name = departmentRowSet.getString("name");
			newDepartmentList.add(mapRowToDepartment(id, name));
		}
		
		return newDepartmentList;
	}

	@Override
	public void updateDepartmentName(Long departmentId, String departmentName) {
		String sqlUpdateDepartment = "UPDATE department SET name =? WHERE department_id =?";
		jdbcTemplate.update(sqlUpdateDepartment, departmentName, departmentId);
	}

	@Override
	public Department createDepartment(String departmentName) {
		String sqlCreateDepartment = "INSERT INTO department (name) VALUES (?) RETURNING department_id";
		long newDepartmentId = jdbcTemplate.queryForObject(sqlCreateDepartment, Long.class, departmentName);
		return mapRowToDepartment(newDepartmentId, departmentName);
	}

	@Override
	public Department getDepartmentById(Long id) {
		String sqlGetDepartmentById = "SELECT name FROM department WHERE department_id =?";
		String name = jdbcTemplate.queryForObject(sqlGetDepartmentById, String.class, id);
		
		return mapRowToDepartment(id, name);
	}
	
	private Department mapRowToDepartment(long id, String name) {
		Department newDepartment = new Department();
		newDepartment.setId(id);
		newDepartment.setName(name);
		return newDepartment;
	}

}
