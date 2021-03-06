package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.dto.work.CommentRequest;
import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.roles.Teacher;
import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.entity.states.WorkMark;
import com.kpfu.itis.khakov.multiremark.entity.type.StageType;
import com.kpfu.itis.khakov.multiremark.entity.work.Comment;
import com.kpfu.itis.khakov.multiremark.entity.work.Review;
import com.kpfu.itis.khakov.multiremark.entity.work.StageStatus;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkStage;
import com.kpfu.itis.khakov.multiremark.service.CommentService;
import com.kpfu.itis.khakov.multiremark.service.ReviewService;
import com.kpfu.itis.khakov.multiremark.service.StudentService;
import com.kpfu.itis.khakov.multiremark.service.TeacherService;
import com.kpfu.itis.khakov.multiremark.service.WorkService;
import com.kpfu.itis.khakov.multiremark.service.WorkStageService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import com.kpfu.itis.khakov.multiremark.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestTeacherController {
	private final WorkService workService;
	private final WorkStageService workStageService;
	private final StudentService studentService;
	private final TeacherService teacherService;
	private final ReviewService reviewService;
	private final CommentService commentService;

	@Autowired
	public RestTeacherController(WorkService workService, WorkStageService workStageService, StudentService studentService, TeacherService teacherService, ReviewService reviewService, CommentService commentService) {
		this.workService = workService;
		this.workStageService = workStageService;
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.reviewService = reviewService;
		this.commentService = commentService;
	}

	@PostMapping(ApplicationUrls.WORK_MARK)
	public ResponseEntity<WorkMark> teacherMark(@PathVariable Long id, @RequestBody WorkMark workMark) {
		WorkStage stage = workStageService.getStageById(id);
		stage.setStageStatus(StageStatus.DONE);
		stage.setResult(workMark.toString());
		Work work = workService.getWorkById(stage.getWork().getId());
		work.setWorkMark(workMark);
		workService.saveWork(work);
		return ResponseEntity.ok(workMark);
	}

	@PostMapping(ApplicationUrls.CREATE_COMMENT)
	public ResponseEntity<Long> addComment(@RequestBody CommentRequest commentRequest, @PathVariable Long reviewId) {
		Review review = reviewService.getReviewById(reviewId);
		Long id = 0L;
		if (review != null) {
			Comment comment = null;
			if (commentRequest != null && commentRequest.getId() != null) {
				comment = commentService.get(commentRequest.getId());
				comment.setReview(review);
			}
			if (comment == null) {
				comment = new Comment();
				comment.setReview(review);
			}
			comment.setBeginString(commentRequest.getLine());
			comment.setComment(commentRequest.getComment());
			id = commentService.save(comment).getId();
		}
		return ResponseEntity.ok(id);
	}

	@PostMapping(ApplicationUrls.CREATE_REVIEW)
	public ResponseEntity<Review> addReview(@PathVariable Long workId) {
		Review review = new Review();
		WorkStage stage = workStageService.getStageById(workId);
		if (stage != null && StageType.isReviewStage(stage.getStage().getType())) {
			User user = SecurityUtils.getCurrentUser();
			Student student = null;
			Teacher teacher = null;

			if (user.getRole() == Role.ROLE_STUDENT) {
				student = studentService.getStudent(user.getId());
				review.setStudent(student);
			} else {
				teacher = teacherService.getTeacherById(user.getId());
				review.setTeacher(teacher);
			}
			stage.setStageStatus(StageStatus.IN_PROCESS);
			review.setWorkStage(stage);
			review = reviewService.addReview(review);
			review.setStudent(null);
			review.setWorkStage(null);
			review.setTeacher(null);
			review.setStudent(null);
			List<Comment> comments = review.getComments();
			if (comments != null) {
				comments.forEach(c -> c.setReview(null));
			}
		}
		return ResponseEntity.ok(review);
	}

	@PostMapping(ApplicationUrls.REVIEW_DONE)
	public ResponseEntity<Long> review(@PathVariable Long id) {
		Review review = reviewService.getReviewById(id);
		if (review != null) {
			review.setReviewStatus("Done");
			review.getWorkStage().setStageStatus(StageStatus.DONE);
			workStageService.save(review.getWorkStage());
			reviewService.save(review);
		}
		return ResponseEntity.ok(id);
	}

	@GetMapping(ApplicationUrls.CREATE_REVIEW)
	public ResponseEntity<Review> getReview(@PathVariable Long workId) {
		Review review = new Review();
		review.setId(0L);
		WorkStage stage = workStageService.getStageById(workId);
		if (stage != null) {
			stage.setWork(null);
			review = stage.getReview();
			if (review != null) {
				review.setStudent(null);
				review.setWorkStage(null);
				review.setTeacher(null);
				review.setStudent(null);
				List<Comment> comments = review.getComments();
				if (comments != null) {
					comments.forEach(c -> c.setReview(null));
				}
			} else {
				review = new Review();
				review.setId(0L);
			}
		}
		return ResponseEntity.ok(review);
	}
}
