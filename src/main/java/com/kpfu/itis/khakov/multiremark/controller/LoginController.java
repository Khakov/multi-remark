package com.kpfu.itis.khakov.multiremark.controller;

/**
 * @author Rustam Khakov
 */

import com.kpfu.itis.khakov.multiremark.entity.task.TaskStage;
import com.kpfu.itis.khakov.multiremark.entity.type.StageType;
import com.kpfu.itis.khakov.multiremark.entity.type.TaskType;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkType;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkTypeRepository;
import com.kpfu.itis.khakov.multiremark.service.TaskStageService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
	private final TaskStageService taskStageService;
	private final WorkTypeRepository repository;

	@Autowired
	public LoginController(TaskStageService taskStageService, WorkTypeRepository repository) {
		this.taskStageService = taskStageService;
		this.repository = repository;
	}

	@GetMapping(value = ApplicationUrls.TEST)
	public String getLoginPage() {
//		List<TaskStage> stages = taskStageService.findAll();
		taskStageService.findAll();
		for (StageType stageType : StageType.values()) {
			TaskStage stage = new TaskStage();
			stage.setType(stageType);
			if (StageType.isReviewStage(stageType)) {
				stage.setWorkTypes(new ArrayList<>());
				stage.getWorkTypes().add(repository.findFirstByType(TaskType.CODE));
				stage.getWorkTypes().add(repository.findFirstByType(TaskType.TEXT));
				stage.getWorkTypes().add(repository.findFirstByType(TaskType.PROJECT));
			}
			if (StageType.AUTOMATE_TEST == stageType || StageType.TEACHER_REMARK == stageType) {
				stage.setWorkTypes(new ArrayList<>());
				stage.getWorkTypes().add(repository.findFirstByType(TaskType.CODE));
				stage.getWorkTypes().add(repository.findFirstByType(TaskType.TEXT));
				stage.getWorkTypes().add(repository.findFirstByType(TaskType.PROJECT));
				stage.getWorkTypes().add(repository.findFirstByType(TaskType.TEST));
			}

			taskStageService.save(stage);
		}
		return "log";
	}
}
