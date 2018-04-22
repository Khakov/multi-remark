/*
package com.kpfu.itis.khakov.multiremark.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

*/
/**
 * @author Rustam Khakov
 *//*

@NodeEntity
public class Course {
	@Id @GeneratedValue
	private Long id;
	private String name;
	@Relationship(type = "LEARN", direction = Relationship.INCOMING)
	private List<Student> students;
	@Relationship(type = "HAVE_TASK")
	private List<Task> tasks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
*/
