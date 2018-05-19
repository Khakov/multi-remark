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
	private Teacher teacher;

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

	@Relationship(type = "TESTS")
	private List<TestCase> tests;

	public Task() {
	}

	private int maxEdit;

	public int getMaxEdit() {
		return maxEdit;
	}

	public void setMaxEdit(int maxEdit) {
		this.maxEdit = maxEdit;
	}

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

	public List<TestCase> getTests() {
		return tests;
	}

	public void setTests(List<TestCase> tests) {
		this.tests = tests;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Task task = (Task) o;

		if (maxEdit != task.maxEdit) return false;
		if (id != null ? !id.equals(task.id) : task.id != null) return false;
		if (name != null ? !name.equals(task.name) : task.name != null) return false;
		if (text != null ? !text.equals(task.text) : task.text != null) return false;
		if (teacher != null ? !teacher.equals(task.teacher) : task.teacher != null) return false;
		if (works != null ? !works.equals(task.works) : task.works != null) return false;
		if (standarts != null ? !standarts.equals(task.standarts) : task.standarts != null) return false;
		if (workType != null ? !workType.equals(task.workType) : task.workType != null) return false;
		if (stages != null ? !stages.equals(task.stages) : task.stages != null) return false;
		return questions != null ? questions.equals(task.questions) : task.questions == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (text != null ? text.hashCode() : 0);
		result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
		result = 31 * result + (works != null ? works.hashCode() : 0);
		result = 31 * result + (standarts != null ? standarts.hashCode() : 0);
		result = 31 * result + (workType != null ? workType.hashCode() : 0);
		result = 31 * result + (stages != null ? stages.hashCode() : 0);
		result = 31 * result + (questions != null ? questions.hashCode() : 0);
		result = 31 * result + maxEdit;
		return result;
	}
}
