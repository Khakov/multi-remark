package com.kpfu.itis.khakov.multiremark.dto.task;

import com.kpfu.itis.khakov.multiremark.dto.question.QuestionResponse;
import com.kpfu.itis.khakov.multiremark.dto.work.WorkResponse;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.task.TaskStandart;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rustam Khakov
 */
public class TaskResponse {
	private Long id;

	private String name;

	private String text;

	private List<WorkResponse> works;

	private List<TaskStandart> standarts;

	private WorkTypeResponse workType;

	private List<TaskStageResponse> stages;

	private List<QuestionResponse> questions;

	public TaskResponse(Task task) {
		setId(task.getId());
		setName(task.getName());
		setQuestions(task.getQuestions()
				.stream().map(QuestionResponse::new).collect(Collectors.toList()));

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<WorkResponse> getWorks() {
		return works;
	}

	public void setWorks(List<WorkResponse> works) {
		this.works = works;
	}

	public List<TaskStandart> getStandarts() {
		return standarts;
	}

	public void setStandarts(List<TaskStandart> standarts) {
		this.standarts = standarts;
	}

	public WorkTypeResponse getWorkType() {
		return workType;
	}

	public void setWorkType(WorkTypeResponse workType) {
		this.workType = workType;
	}

	public List<TaskStageResponse> getStages() {
		return stages;
	}

	public void setStages(List<TaskStageResponse> stages) {
		this.stages = stages;
	}

	public List<QuestionResponse> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionResponse> questions) {
		this.questions = questions;
	}
}
