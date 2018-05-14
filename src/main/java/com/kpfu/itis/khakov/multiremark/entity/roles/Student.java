package com.kpfu.itis.khakov.multiremark.entity.roles;

import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author Rustam Khakov
 */

@NodeEntity
public class Student extends User{

	private String name;

	@Relationship
	private List<Work> works;

	@Relationship(type = "STUDENT_TEACHER", direction = Relationship.INCOMING)
	private List<Teacher> teachers;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Work> getWorks() {
		return works;
	}

	public void setWorks(List<Work> works) {
		this.works = works;
	}

	/*public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}*/

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
}
