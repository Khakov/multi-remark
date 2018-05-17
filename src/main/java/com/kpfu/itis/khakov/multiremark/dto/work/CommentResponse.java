package com.kpfu.itis.khakov.multiremark.dto.work;

/**
 * @author Rustam Khakov
 */
public class CommentResponse {
	String text;
	String comment;

	public CommentResponse(String text, String comment) {
		this.text = text;
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
