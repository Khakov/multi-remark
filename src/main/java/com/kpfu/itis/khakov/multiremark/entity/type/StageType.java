package com.kpfu.itis.khakov.multiremark.entity.type;

/**
 * @author Rustam Khakov
 */
public enum StageType {
	PEER_REVIEW, SELF_REVIEW, TEACHER_REMARK, AUTOMATE_TEST, QUEUE_PEER_REVIEW, OTHER, TEACHER_REVIEW;

	public static boolean isReviewStage(StageType stageType) {
		return stageType == PEER_REVIEW || SELF_REVIEW == stageType || QUEUE_PEER_REVIEW == stageType || TEACHER_REVIEW == stageType;
	}
}
