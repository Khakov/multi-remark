package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.entity.task.Question;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.service.QuestionService;
import com.kpfu.itis.khakov.multiremark.service.TaskService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import com.kpfu.itis.khakov.multiremark.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestQuestionController {

	private final QuestionService questionService;
	private final TaskService taskService;

	@Autowired
	public RestQuestionController(QuestionService questionService, TaskService taskService) {
		this.questionService = questionService;
		this.taskService = taskService;
	}

	@PostMapping(ApplicationUrls.ADD_QUESTION)
	public ResponseEntity<Question> createQuestion(@RequestBody Question question, @PathVariable("taskId") Long taskId) {
		SecurityUtils.getCurrentUser();
		Task task = taskService.getTaskById(taskId);
		question.addTask(task);
		return ResponseEntity.ok(questionService.createQuestion(question));
	}
}
