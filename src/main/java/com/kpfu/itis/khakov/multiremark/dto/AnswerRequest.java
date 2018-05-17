package com.kpfu.itis.khakov.multiremark.dto;

import java.util.List;

/**
 * @author Rustam Khakov
 */
public class AnswerRequest {
	private Long taskId;
	private List<AnswerIdRequest> answerIds;
	private String text;

	public AnswerRequest() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public List<AnswerIdRequest> getAnswerIds() {
		return answerIds;
	}

	public void setAnswerIds(List<AnswerIdRequest> answerIds) {
		this.answerIds = answerIds;
	}
}
