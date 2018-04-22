/*
package com.kpfu.itis.khakov.multiremark.entity;

import com.kpfu.itis.khakov.multiremark.entity.relationships.WorkStatusRelationship;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

*/
/**
 * @author Rustam Khakov
 *//*

@NodeEntity
public class WorkStatus {
	@Id @GeneratedValue
	private Long id;

	@Relationship(type = "WORK_STATUS", direction = Relationship.INCOMING)
	private Work work;
	@Relationship(type = "NEXT_STATUS")
	private List<WorkStatus> previousStatuses;
	@Relationship(type = "NEXT_STATUS", direction = Relationship.INCOMING)
	private List<WorkStatusRelationship> nextStatuses;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public List<WorkStatus> getPreviousStatuses() {
		return previousStatuses;
	}

	public void setPreviousStatuses(List<WorkStatus> previousStatuses) {
		this.previousStatuses = previousStatuses;
	}

	public List<WorkStatusRelationship> getNextStatuses() {
		return nextStatuses;
	}

	public void setNextStatuses(List<WorkStatusRelationship> nextStatuses) {
		this.nextStatuses = nextStatuses;
	}
}
*/
