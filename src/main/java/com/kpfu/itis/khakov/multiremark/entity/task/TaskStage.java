package com.kpfu.itis.khakov.multiremark.entity.task;

import com.kpfu.itis.khakov.multiremark.entity.type.StageType;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkStage;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkType;
import com.kpfu.itis.khakov.multiremark.service.WorkService;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class TaskStage {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private StageType type;

	private String input;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	@Relationship(type = "WORK_STAGES", direction = Relationship.OUTGOING)
	private List<WorkStage> stages;

	@Relationship(type = "TASK_TYPES", direction = Relationship.INCOMING)
	private List<Task> tasks;

	@Relationship(type = "TASK_STAGES", direction = Relationship.INCOMING)
	private List<WorkType> workTypes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<WorkStage> getStages() {
		return stages;
	}

	public void setStages(List<WorkStage> stages) {
		this.stages = stages;
	}

	public void addWorkStage(WorkStage stage) {
		if (stages == null) {
			stages = Collections.singletonList(stage);
		} else {
			stages.add(stage);
		}
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<WorkType> getWorkTypes() {
		return workTypes;
	}

	public void setWorkTypes(List<WorkType> workTypes) {
		this.workTypes = workTypes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StageType getType() {
		return type;
	}

	public void setType(StageType type) {
		this.type = type;
	}

}
