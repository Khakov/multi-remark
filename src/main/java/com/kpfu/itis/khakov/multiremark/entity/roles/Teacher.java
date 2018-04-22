package com.kpfu.itis.khakov.multiremark.entity.roles;

import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.Review;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class Teacher extends User{
	/*@Id
	@GeneratedValue
	private Long id;*/

	private String name;


	@Relationship(type = "STUDENT_TEACHER")
	private List<Student> students;

	@Relationship
	Review review;

	@Relationship
	List<Task> tasks;

/*	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
