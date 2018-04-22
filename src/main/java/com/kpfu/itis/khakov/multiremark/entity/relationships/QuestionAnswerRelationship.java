package com.kpfu.itis.khakov.multiremark.entity.relationships;

import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import com.kpfu.itis.khakov.multiremark.entity.task.Question;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @author Rustam Khakov
 */
@RelationshipEntity(type = "QUESTION_ANSWERS")
public class QuestionAnswerRelationship {
	@Id
	@GeneratedValue
	private Long id;
	@StartNode
	private Question question;
	@EndNode
	private Answer answer;

	private boolean isRight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean right) {
		isRight = right;
	}
}
