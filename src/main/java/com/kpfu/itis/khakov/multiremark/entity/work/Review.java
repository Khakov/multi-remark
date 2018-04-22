package com.kpfu.itis.khakov.multiremark.entity.work;

import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.roles.Teacher;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	@Relationship
	private Teacher teacher;

	@Relationship
	private Student student;

	private String reviewType;

	@Relationship(direction = Relationship.INCOMING)
	private WorkStage workStage;

	private String reviewStatus;

	@Relationship
	private List<Comment> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getReviewType() {
		return reviewType;
	}

	public void setReviewType(String reviewType) {
		this.reviewType = reviewType;
	}

	public WorkStage getWorkStage() {
		return workStage;
	}

	public void setWorkStage(WorkStage workStage) {
		this.workStage = workStage;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
