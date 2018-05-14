package com.kpfu.itis.khakov.multiremark.dto.answer;

import com.kpfu.itis.khakov.multiremark.entity.task.Answer;

/**
 * @author Rustam Khakov
 */
public class AnswerResponse {
	private Long id;

	private String value;

	private boolean isRight;

	public AnswerResponse(Answer answer) {
		setId(answer.getId());
		setRight(answer.isRight());
		setValue(answer.getValue());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean right) {
		isRight = right;
	}
}
