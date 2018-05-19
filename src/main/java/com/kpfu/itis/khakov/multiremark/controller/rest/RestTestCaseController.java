package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.task.TestCase;
import com.kpfu.itis.khakov.multiremark.service.TaskService;
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
public class RestTestCaseController {
	private final TestCaseService testCaseService;
	private final TaskService taskService;

	@Autowired
	public RestTestCaseController(TestCaseService testCaseService, TaskService taskService) {
		this.testCaseService = testCaseService;
		this.taskService = taskService;
	}

	@PostMapping(ApplicationUrls.ADD_TEST_CASE)
	public ResponseEntity<String> add(@PathVariable Long id, @RequestBody TestCase testCase) {
		Task task = taskService.getTaskById(id);
		testCase.setTask(task);
		testCaseService.save(testCase);
		return ResponseEntity.ok(null);
	}

	@GetMapping(ApplicationUrls.ADD_TEST_CASE)
	public ResponseEntity<TestCase> get(@PathVariable Long id) {
		TestCase testCase = testCaseService.get(id);
		testCase.setTask(null);
		return ResponseEntity.ok(testCase);
	}
}
