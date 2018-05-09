package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.dto.AnswerRequest;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkAnswer;
import com.kpfu.itis.khakov.multiremark.service.AnswerService;
import com.kpfu.itis.khakov.multiremark.service.TaskService;
import com.kpfu.itis.khakov.multiremark.service.WorkService;
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
public class RestWorkController {
	private final TaskService taskService;
	private final AnswerService answerService;
	private final WorkService workService;

	@Autowired
	public RestWorkController(TaskService taskService, AnswerService answerService, WorkService workService) {
		this.taskService = taskService;
		this.answerService = answerService;
		this.workService = workService;
	}


	@PostMapping(ApplicationUrls.QUESTION_ANSWER)
	public ResponseEntity answerOnQuestion(@RequestBody AnswerRequest request) {
		Student student = (Student) SecurityUtils.getCurrentUser();
		Task task = taskService.getTaskById(request.getTaskId());
		Answer answer = answerService.getAnswerById(request.getAnswerId());
		workService.createWork(student, task, answer, request.getText());
		return ResponseEntity.ok().build();
	}

	@GetMapping(ApplicationUrls.GET_WORKS)
	public ResponseEntity<List<Work>> getWorksId(){
		Student student = (Student) SecurityUtils.getCurrentUser();
		return ResponseEntity.ok(workService.getWorksByStudent(student));
	}
}
