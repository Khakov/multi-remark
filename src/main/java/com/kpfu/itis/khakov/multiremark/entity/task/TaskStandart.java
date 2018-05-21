package com.kpfu.itis.khakov.multiremark.entity.task;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class TaskStandart {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String text;

	@Relationship(type = "STANDARTS", direction = Relationship.INCOMING)
	private Task task;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
