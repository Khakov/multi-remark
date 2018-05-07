package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.service.StudentService;
import com.kpfu.itis.khakov.multiremark.service.TaskService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import com.kpfu.itis.khakov.multiremark.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestTaskController {
	private final TaskService taskService;

	@Autowired
	public RestTaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping(ApplicationUrls.CREATE_TASK)
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		return null;
	}

	@GetMapping(ApplicationUrls.GET_TASKS)
	public ResponseEntity<List<Task>> getTasks() {
		/*User user = SecurityUtils.getCurrentUser();
		Role role = SecurityUtils.getUserAuthtority(user);
		if (role == Role.ROLE_STUDENT) {
		} else {
			tasks = taskService.getAllTasks();//todo
		}*/
		return ResponseEntity.ok(taskService.getAllTasks());
	}
}
