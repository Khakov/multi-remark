package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.dto.work.CommentResponse;
import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.task.TaskStage;
import com.kpfu.itis.khakov.multiremark.entity.type.StageType;
import com.kpfu.itis.khakov.multiremark.entity.type.TaskType;
import com.kpfu.itis.khakov.multiremark.entity.work.Comment;
import com.kpfu.itis.khakov.multiremark.entity.work.Review;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkStage;
import com.kpfu.itis.khakov.multiremark.service.ReviewService;
import com.kpfu.itis.khakov.multiremark.service.TaskService;
import com.kpfu.itis.khakov.multiremark.service.WorkService;
import com.kpfu.itis.khakov.multiremark.service.WorkStageService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import com.kpfu.itis.khakov.multiremark.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestStagesController {
	private final WorkStageService workStageService;
	private final WorkService workService;
	private final TaskService taskService;
	private final ReviewService reviewService;


	@Autowired
	public RestStagesController(WorkService workService, WorkStageService workStageService, TaskService taskService, ReviewService reviewService) {
		this.workService = workService;
		this.workStageService = workStageService;
		this.taskService = taskService;
		this.reviewService = reviewService;
	}

	@GetMapping(ApplicationUrls.GET_WORK_STAGES)
	public ResponseEntity<List<WorkStage>> getStages(@PathVariable Long id) {
		List<WorkStage> stages = new ArrayList<>();
		Work work = workService.getWorkById(id);
		if (work.getWorkStages() != null) {
			stages = work.getWorkStages();
			stages.forEach(s -> {
				s.setWork(null);
				s.setReview(null);
				s.setPreviousStage(null);
				TaskStage stage = s.getStage();
				if (stage != null) {
					stage.setStages(null);
					stage.setTasks(null);
					stage.setWorkTypes(null);
				}
			});
		}
		return ResponseEntity.ok(stages);
	}

	@GetMapping(ApplicationUrls.GET_WORK_STAGE)
	public ResponseEntity<WorkStage> getStage(@PathVariable Long id) {
		WorkStage workStage = workStageService.getStageById(id);
		if (workStage != null) {
			workStage.setWork(null);
			workStage.setReview(null);
			workStage.setPreviousStage(null);
			TaskStage stage = workStage.getStage();
			if (stage != null) {
				stage.setStages(null);
				stage.setTasks(null);
				stage.setWorkTypes(null);
			}
		}
		return ResponseEntity.ok(workStage);
	}

	@GetMapping(ApplicationUrls.GET_WORK_STAGES_EDIT)
	public ResponseEntity<Boolean> getStageEdit(@PathVariable Long id) {
		WorkStage workStage = workStageService.getStageById(id);
		User user = SecurityUtils.getCurrentUser();
		boolean isEdit = false;
		Work work = workService.getWorkById(workStage.getWork().getId());
		if (workStage.getStage().getType() == StageType.TEACHER_REVIEW) {
			if (user.getRole() == Role.ROLE_TEACHER) {
				isEdit = true;
			}
		} else if (workStage.getStage().getType() == StageType.SELF_REVIEW) {
			isEdit = work.getStudent().getId().equals(user.getId());
		} else if (workStage.getStage().getType() == StageType.PEER_REVIEW) {
			isEdit = !user.getId().equals(work.getStudent().getId());
		}
		return ResponseEntity.ok(isEdit);
	}

	@GetMapping(ApplicationUrls.GET_WORK_CODE)
	public ResponseEntity<List<CommentResponse>> getWorkCode(
			@PathVariable Long id) {
		WorkStage work = workStageService.getStageById(id);
		List<CommentResponse> lines = new ArrayList<>();
		if (work != null) {
			Work  w = workService.getWorkById(work.getWork().getId());
			Task task = taskService.getTaskById(w.getTask().getId());
			if (task.getWorkType().getType() == TaskType.TEXT || task.getWorkType().getType() == TaskType.CODE) {
				String text = w.getWorkAnswer().getText();
				if (text != null) {
					Map<Integer, Comment> commentMap = new HashMap<>();
					if (work.getReview() != null) {
						Review review = reviewService.getReviewById(work.getReview().getId());
						if (review.getComments()!= null) {
							commentMap = work.getReview().getComments().stream().collect(
									Collectors.toMap(Comment::getBeginString, comment -> comment));
						}
					}
					String[] texts = text.split("\n");
					for (int i = 0; i < texts.length; i++) {
						Comment comment = commentMap.getOrDefault(i, new Comment());
						lines.add(new CommentResponse(texts[i], comment.getComment(), comment.getId()));
					}
				}
			}
		}
		return ResponseEntity.ok(lines);
	}
}
