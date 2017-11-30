package com.techelevator.projects.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
		List<Project> newProjectList = new ArrayList<>();
		String sqlAllActiveProjects = "SELECT * FROM project WHERE (to_date IS NOT NULL AND to_date >= CURRENT_DATE) OR (from_date < CURRENT_DATE AND to_date IS NULL) ORDER BY name";
		
		SqlRowSet projectRowSet = jdbcTemplate.queryForRowSet(sqlAllActiveProjects);
		
		while (projectRowSet.next()) {
			newProjectList.add(mapRowToProject(projectRowSet));
		}
		
		return newProjectList;
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		String sqlRemoveProject = "DELETE FROM project_employee WHERE project_id =? AND employee_id =?";
		jdbcTemplate.update(sqlRemoveProject, projectId, employeeId);
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		String sqlAddEmployeeToProject = "INSERT INTO project_employee (project_id, employee_id) VALUES (?,?)";
		jdbcTemplate.update(sqlAddEmployeeToProject, projectId, employeeId);
	}

	private Project mapRowToProject(SqlRowSet projectRowSet) {
		Project newProject = new Project();
		long projectId = projectRowSet.getLong("project_id");
		String name = projectRowSet.getString("name");
		
		if(projectRowSet.getDate("from_date")!=null){
			LocalDate startDate = projectRowSet.getDate("from_date").toLocalDate();
			newProject.setStartDate(startDate);
		}
		if(projectRowSet.getDate("to_date")!=null){
			LocalDate endDate = projectRowSet.getDate("to_date").toLocalDate();
			newProject.setEndDate(endDate);
		}
		
		newProject.setId(projectId);
		newProject.setName(name);
	
		return newProject;
	}
}
