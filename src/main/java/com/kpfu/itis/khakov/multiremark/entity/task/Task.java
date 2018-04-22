package com.kpfu.itis.khakov.multiremark.entity.task;

import com.kpfu.itis.khakov.multiremark.entity.roles.Teacher;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkType;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author Rustam Khakov
 */
public class Task {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String text;

	@Relationship
	Teacher teacher;

	@Relationship
	private List<Work> works;

	@Relationship(type = "STANDARTS")
	private List<TaskStandart> standarts;

	@Relationship(type = "TASK_TYPES")
	private WorkType workType;

	@Relationship(type = "TASK_STAGES")
	private List<TaskStage> stages;

	@Relationship(type = "QUESTION")
	private List<Question> questions;


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

	public List<Work> getWorks() {
		return works;
	}

	public void setWorks(List<Work> works) {
		this.works = works;
	}

	public List<TaskStandart> getStandarts() {
		return standarts;
	}

	public void setStandarts(List<TaskStandart> standarts) {
		this.standarts = standarts;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public List<TaskStage> getStages() {
		return stages;
	}

	public void setStages(List<TaskStage> stages) {
		this.stages = stages;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
