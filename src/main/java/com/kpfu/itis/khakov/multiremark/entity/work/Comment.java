package com.kpfu.itis.khakov.multiremark.entity.work;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Rustam Khakov
 */
@NodeEntity
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	@Relationship(type = "COMMENT", direction = Relationship.INCOMING)
	private Review review;
	private String comment;
	private int beginString;
	private int endString;
	private int beginIndex;
	private int endIndex;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getBeginString() {
		return beginString;
	}

	public void setBeginString(int beginString) {
		this.beginString = beginString;
	}

	public int getEndString() {
		return endString;
	}

	public void setEndString(int endString) {
		this.endString = endString;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
}
