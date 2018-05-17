package com.kpfu.itis.khakov.multiremark.entity.work;

import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.states.WorkMark;
import com.kpfu.itis.khakov.multiremark.entity.states.WorkState;
import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.List;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class Work {
	@Id
	@GeneratedValue
	private Long id;

	public Work() {
	}

	private String name;
	@Relationship(type = "STUDENT_WORK", direction = Relationship.INCOMING)
	private Student student;

	@Relationship(direction = Relationship.INCOMING)
	private Task task;

	@Relationship(type = "PREVIOUS_WORK")
	private Work previousWork;

	@Relationship(type = "PREVIOUS_WORK", direction = Relationship.INCOMING)
	private Work nextWork;

	@Relationship(type = "WORK_ANSWER")
	private WorkAnswer workAnswer;

	@Relationship
	private List<Answer> answers;

	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public WorkAnswer getWorkAnswer() {
		return workAnswer;
	}

	public void setWorkAnswer(WorkAnswer workAnswer) {
		this.workAnswer = workAnswer;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/*	@Relationship(type = "WORK_STATUS")
	private WorkStatus status;*/

	@Relationship
	private List<WorkStage> workStages;

	public void addWorkStage(WorkStage stage) {
		if (workStages == null) {
			workStages = Collections.singletonList(stage);
		} else {
			workStages.add(stage);
		}
	}

	private WorkState state;

	private WorkMark workMark;

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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Work getPreviousWork() {
		return previousWork;
	}

	public void setPreviousWork(Work previousWork) {
		this.previousWork = previousWork;
	}

	public Work getNextWork() {
		return nextWork;
	}

	public void setNextWork(Work nextWork) {
		this.nextWork = nextWork;
	}

	public WorkState getState() {
		return state;
	}

	public void setState(WorkState state) {
		this.state = state;
	}

	public WorkMark getWorkMark() {
		return workMark;
	}

	public void setWorkMark(WorkMark workMark) {
		this.workMark = workMark;
	}

	public List<WorkStage> getWorkStages() {
		return workStages;
	}

	public void setWorkStages(List<WorkStage> workStages) {
		this.workStages = workStages;
	}
}

