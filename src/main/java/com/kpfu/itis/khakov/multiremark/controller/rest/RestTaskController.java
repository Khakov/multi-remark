package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.service.StudentService;
import com.kpfu.itis.khakov.multiremark.service.TaskService;
import com.kpfu.itis.khakov.multiremark.service.WorkService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import com.kpfu.itis.khakov.multiremark.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestTaskController {
	private final TaskService taskService;
	private final WorkService workService;

	@Autowired
	public RestTaskController(TaskService taskService, WorkService workService) {
		this.taskService = taskService;
		this.workService = workService;
	}

	@PostMapping(ApplicationUrls.CREATE_TASK)
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		return ResponseEntity.ok(taskService.createTask(task));
	}

	@GetMapping(ApplicationUrls.GET_TASKS)
	public ResponseEntity<List<Task>> getTasks() {
		/*User user = SecurityUtils.getCurrentUser();
		Role role = SecurityUtils.getUserAuthtority(user);
		if (role == Role.ROLE_STUDENT) {
		} else {
			tasks = taskService.getAllTasks();//todo
		}*/
		List<Task> tasks = taskService.getAllTasks();
		return ResponseEntity.ok(tasks);
	}

	@GetMapping(ApplicationUrls.GET_TASK)
	public ResponseEntity<Task> getTask(@PathVariable("taskId") Long taskId) {
		return ResponseEntity.ok(taskService.getTaskByIdResponse(taskId));
	}

	@GetMapping(ApplicationUrls.GET_TASKS_NOT_COMPLETED)
	public ResponseEntity<Map<Boolean, List<Task>>> getNotCompletedTasks() {
		Student student = (Student) SecurityUtils.getCurrentUser();
		List<Task> tasks = workService.getWorksByStudent(student).stream().map(Work::getTask).collect(Collectors.toList());
		taskService.prepareTasks(tasks);
		List<Task> allTasks = taskService.getAllTasks();
		List<Task> filteredTasks = taskService.getAllTasks().stream().filter(tasks::contains).collect(Collectors.toList());
		allTasks.removeIf(filteredTasks::contains);
		Map<Boolean, List<Task>> taskMap = new HashMap<>();
		taskMap.put(false, allTasks);
		taskMap.put(true, filteredTasks);
		return ResponseEntity.ok(taskMap);
	}
}
