package com.kpfu.itis.khakov.multiremark.dto;

/**
 * @author Rustam Khakov
 */
public class AnswerRequest {
	private Long taskId;
	private Long answerId;
	private String text;

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

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
}
