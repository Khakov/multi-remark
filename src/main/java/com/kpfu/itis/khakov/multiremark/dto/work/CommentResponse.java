package com.kpfu.itis.khakov.multiremark.dto.work;

/**
 * @author Rustam Khakov
 */
public class CommentResponse {
	private String text;
	private CommentText comment;

	public CommentResponse(String text, String comment, Long id) {
		this.text = text;
		this.comment = new CommentText(comment, id);
	}

	public CommentResponse() {
	}

	public CommentText getComment() {
		return comment;
	}

	public void setComment(CommentText comment) {
		this.comment = comment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public class CommentText {
		private String comment;
		private Long id;

		public CommentText() {
		}

		public CommentText(String comment, Long id) {
			this.comment = comment;
			this.id = id;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
	}
}
