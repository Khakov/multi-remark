package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.task.TaskStandart;
import com.kpfu.itis.khakov.multiremark.entity.task.TestCase;
import com.kpfu.itis.khakov.multiremark.service.TaskService;
import com.kpfu.itis.khakov.multiremark.service.TaskStandartService;
import com.kpfu.itis.khakov.multiremark.service.TestCaseService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestTaskStandartController {
	private final TaskStandartService taskStandartService;
	private final TaskService taskService;

	@Autowired
	public RestTaskStandartController(TaskStandartService taskStandartService, TaskService taskService) {
		this.taskStandartService = taskStandartService;
		this.taskService = taskService;
	}

	@PostMapping(ApplicationUrls.ADD_TASK_STANDART)
	public ResponseEntity<String> add(@PathVariable Long id, @RequestBody TaskStandart taskStandart) {
		Task task = taskService.getTaskById(id);
		taskStandart.setTask(task);
		taskStandartService.save(taskStandart);
		return ResponseEntity.ok(null);
	}

	@GetMapping(ApplicationUrls.ADD_TASK_STANDART)
	public ResponseEntity<TaskStandart> get(@PathVariable Long id) {
		TaskStandart taskStandart = taskStandartService.get(id);
		taskStandart.setTask(null);
		return ResponseEntity.ok(taskStandart);
	}
}
