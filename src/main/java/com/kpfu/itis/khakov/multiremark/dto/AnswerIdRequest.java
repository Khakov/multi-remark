package com.kpfu.itis.khakov.multiremark.dto;

/**
 * @author Rustam Khakov
 */
public class AnswerIdRequest {
	private Long answerId;
	private Long questionId;

	public AnswerIdRequest() {
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
}
