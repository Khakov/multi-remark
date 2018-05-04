package com.kpfu.itis.khakov.multiremark.entity.task;

import com.kpfu.itis.khakov.multiremark.entity.type.QuestionType;
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
public class Question {
	@Id
	@GeneratedValue
	private Long id;

	private String value;

	@Relationship(type = "QUESTION_ANSWERS")
	private List<Answer> answers;

	@Relationship(type = "QUESTION", direction = Relationship.INCOMING)
	private List<Task> tasks;

	private QuestionType type;

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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public void addTask(Task task) {
		if (task != null) {
			if (tasks == null) {
				tasks = Collections.singletonList(task);
			} else {
				tasks.add(task);
			}
		}
	}
}
