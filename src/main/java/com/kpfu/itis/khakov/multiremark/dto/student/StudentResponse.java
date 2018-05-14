package com.kpfu.itis.khakov.multiremark.dto.student;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;

/**
 * @author Rustam Khakov
 */
public class StudentResponse {
	protected Long id;

	protected Role role;

	protected String email;

	private String name;

	public StudentResponse(Student student) {
		setEmail(student.getEmail());
		setId(student.getId());
		setRole(student.getRole());
		setName(student.getName());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
