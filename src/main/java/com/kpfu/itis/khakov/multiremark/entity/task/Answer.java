package com.kpfu.itis.khakov.multiremark.entity.task;

import com.kpfu.itis.khakov.multiremark.entity.relationships.QuestionAnswerRelationship;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class Answer {
	@Id
	@GeneratedValue
	private Long id;

	private String value;

	private boolean isRight;

	@Relationship(type = "QUESTION_ANSWERS", direction =  Relationship.INCOMING)
	private List<QuestionAnswerRelationship> questions;

	@Relationship(type = "WORK_ANSWERS", direction =  Relationship.INCOMING)
	private List<Work> works;

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

	public List<QuestionAnswerRelationship> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionAnswerRelationship> questions) {
		this.questions = questions;
	}

	public List<Work> getWorks() {
		return works;
	}

	public void setWorks(List<Work> works) {
		this.works = works;
	}
}
