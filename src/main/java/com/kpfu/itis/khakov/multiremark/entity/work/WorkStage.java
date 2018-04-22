package com.kpfu.itis.khakov.multiremark.entity.work;

import com.kpfu.itis.khakov.multiremark.entity.task.TaskStage;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class WorkStage {
	@Id
	@GeneratedValue
	private Long id;

	@Relationship
	private Review review;

	@Relationship(type = "WORK_STAGES", direction = Relationship.INCOMING)
	private TaskStage stage;

	@Relationship
	private Work work;

	private String result;

	private StageStatus stageStatus;

	@Relationship(type = "PREVIOUS_STAGE")
	private WorkStage previousStage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}


	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public StageStatus getStageStatus() {
		return stageStatus;
	}

	public void setStageStatus(StageStatus stageStatus) {
		this.stageStatus = stageStatus;
	}

	public WorkStage getPreviousStage() {
		return previousStage;
	}

	public void setPreviousStage(WorkStage previousStage) {
		this.previousStage = previousStage;
	}

	public TaskStage getStage() {
		return stage;
	}

	public void setStage(TaskStage stage) {
		this.stage = stage;
	}
}
