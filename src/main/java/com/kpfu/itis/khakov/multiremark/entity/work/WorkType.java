package com.kpfu.itis.khakov.multiremark.entity.work;

import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.task.TaskStage;
import com.kpfu.itis.khakov.multiremark.entity.type.TaskType;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class WorkType {
	@Id
	@GeneratedValue
	private Long id;

	private TaskType type;

	@Relationship(type = "TASK_TYPES", direction = Relationship.INCOMING)
	private List<Task> tasks;

	@Relationship(type = "TASK_STAGES")
	private List<TaskStage> stages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<TaskStage> getStages() {
		return stages;
	}

	public void setStages(List<TaskStage> stages) {
		this.stages = stages;
	}
}
