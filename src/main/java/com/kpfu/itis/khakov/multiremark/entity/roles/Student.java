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
public class Student extends User {

	private String name;

	public Student() {
	}

/*	@Relationship(type ="STUDENT_WORK", direction = Relationship.OUTGOING)
	private List<Work> works;*/

	@Relationship(type = "STUDENT_TEACHER", direction = Relationship.INCOMING)
	private List<Teacher> teachers;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public List<Work> getWorks() {
		return works;
	}

	public void setWorks(List<Work> works) {
		this.works = works;
	}*/

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		if (name != null ? !name.equals(student.name) : student.name != null) return false;
		return teachers != null ? teachers.equals(student.teachers) : student.teachers == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (teachers != null ? teachers.hashCode() : 0);
		return result;
	}
}
