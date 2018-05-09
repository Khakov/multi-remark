package com.kpfu.itis.khakov.multiremark.entity.work;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class WorkAnswer {

	private String text;

	@Relationship(type = "WORK_ANSWER")
	private Work work;

	public WorkAnswer(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}
}
