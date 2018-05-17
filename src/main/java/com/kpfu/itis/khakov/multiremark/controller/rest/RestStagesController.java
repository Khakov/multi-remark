package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.dto.work.CommentResponse;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.task.TaskStage;
import com.kpfu.itis.khakov.multiremark.entity.type.TaskType;
import com.kpfu.itis.khakov.multiremark.entity.work.Comment;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkStage;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestStagesController {
	@Autowired
	WorkStageService workStageService;
	private final WorkService workService;

	@Autowired
	public RestStagesController(WorkService workService) {
		this.workService = workService;
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

	@GetMapping(ApplicationUrls.GET_WORK_CODE)
	public ResponseEntity<List<CommentResponse>> getWorkCode(
			@PathVariable Long workId) {
//		Student student = studentService.getStudent(SecurityUtils.getCurrentUser().getId());
		WorkStage work = workStageService.getStageById(workId);
		List<CommentResponse> lines = new ArrayList<>();
		if (work != null) {
			if (work.getWork().getTask().getWorkType().getType() == TaskType.TEXT || work.getWork().getTask().getWorkType().getType() == TaskType.CODE) {
				String text = work.getWork().getWorkAnswer().getText();
				if (text != null) {
					Map<Integer, String> commentMap = new HashMap<>();
					if (work.getReview() != null && work.getReview().getComments() != null) {
						commentMap = work.getReview().getComments().stream().collect(
								Collectors.toMap(Comment::getBeginString, Comment::getComment));
					}
					String[] texts = text.split("\n");
					for (int i = 0; i < texts.length; i++) {
						lines.add(new CommentResponse(texts[i], commentMap.getOrDefault(i, "")));
					}
				}
			}
		}
		return ResponseEntity.ok(lines);
	}
}
