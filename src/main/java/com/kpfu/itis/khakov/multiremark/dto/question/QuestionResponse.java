package com.kpfu.itis.khakov.multiremark.dto.question;

import com.kpfu.itis.khakov.multiremark.dto.answer.AnswerResponse;
import com.kpfu.itis.khakov.multiremark.entity.task.Question;
import com.kpfu.itis.khakov.multiremark.entity.type.QuestionType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rustam Khakov
 */
public class QuestionResponse {
	private Long id;

	private String value;

	private List<AnswerResponse> answers;

	private QuestionType type;

	public QuestionResponse(Question question) {
		if (question.getAnswers() != null) {
			setAnswers(question.getAnswers().stream().map(AnswerResponse::new).collect(Collectors.toList()));
		}
		setType(question.getType());
		setId(question.getId());
		setValue(question.getValue());
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

	public List<AnswerResponse> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerResponse> answers) {
		this.answers = answers;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}
}
