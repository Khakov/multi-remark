/*
package com.kpfu.itis.khakov.multiremark.entity.relationships;

import com.kpfu.itis.khakov.multiremark.entity.type.TaskType;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkStatus;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.List;

*
 * @author Rustam Khakov


@RelationshipEntity(type = "NEXT_STATUS")
public class WorkStatusRelationship {
	@Id @GeneratedValue
	private Long id;
	@StartNode
	private WorkStatus previousWorkStatus;
	@EndNode
	private WorkStatus nextWorkStatus;
	private List<TaskType> taskTypes;

	public WorkStatus getPreviousWorkStatus() {
		return previousWorkStatus;
	}

	public void setPreviousWorkStatus(WorkStatus previousWorkStatus) {
		this.previousWorkStatus = previousWorkStatus;
	}

	public WorkStatus getNextWorkStatus() {
		return nextWorkStatus;
	}

	public void setNextWorkStatus(WorkStatus nextWorkStatus) {
		this.nextWorkStatus = nextWorkStatus;
	}

	public List<TaskType> getTaskTypes() {
		return taskTypes;
	}

	public void setTaskTypes(List<TaskType> taskTypes) {
		this.taskTypes = taskTypes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
*/
