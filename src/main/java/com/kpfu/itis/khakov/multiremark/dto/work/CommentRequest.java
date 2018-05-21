package com.kpfu.itis.khakov.multiremark.dto.work;

/**
 * @author Rustam Khakov
 */
public class CommentRequest {
	private String comment;
	private int line;
	private Long id;

	public CommentRequest() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
}
