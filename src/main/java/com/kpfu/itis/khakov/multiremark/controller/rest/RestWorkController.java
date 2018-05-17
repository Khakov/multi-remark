package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.dto.AnswerIdRequest;
import com.kpfu.itis.khakov.multiremark.dto.AnswerRequest;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.states.WorkState;
import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.service.AnswerService;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestWorkController {
	private final TaskService taskService;
	private final AnswerService answerService;
	private final WorkService workService;
	private final StudentService studentService;

	@Autowired
	public RestWorkController(TaskService taskService, AnswerService answerService, WorkService workService, StudentService studentService) {
		this.taskService = taskService;
		this.answerService = answerService;
		this.workService = workService;
		this.studentService = studentService;
	}


	@PostMapping(ApplicationUrls.QUESTION_ANSWER)
	public ResponseEntity answerOnQuestion(
			@RequestBody AnswerRequest request) {
		Student student = studentService.getStudent(SecurityUtils.getCurrentUser().getId());
		Task task = taskService.getTaskById(request.getTaskId());
		List<Answer> answers = answerService.getAnswersByIds(
				request.getAnswerIds()
						.stream()
						.map(AnswerIdRequest::getAnswerId)
						.collect(Collectors.toList())
		);
		workService.createWork(student, task, answers, request.getText());
		return ResponseEntity.ok().build();
	}

	@GetMapping(ApplicationUrls.GET_WORKS)
	public ResponseEntity<List<Work>> getWorksId() {
		Student student = studentService.getStudent(SecurityUtils.getCurrentUser().getId());
		return ResponseEntity.ok(workService.getWorksByStudentResponse(student));
	}

	@PostMapping(ApplicationUrls.ADD_WORK)
	public ResponseEntity addWork(
			@RequestBody Work work,
			@PathVariable("id") Long id) {
		Task task = taskService.getTaskById(id);
		Student student = studentService.getStudent(SecurityUtils.getCurrentUser().getId());
		work.setState(WorkState.NEW);
		work.setStudent(student);
		work.setTask(task);
		workService.saveWork(work);
		return ResponseEntity.ok().build();
	}

	@GetMapping(ApplicationUrls.GET_WORKS_BY_TASK)
	public ResponseEntity<List<Work>> getCurrentWorks(
			@PathVariable Long id) {
		Task task = taskService.getTaskById(id);
		Student student = studentService.getStudent(SecurityUtils.getCurrentUser().getId());
		return ResponseEntity.ok(workService.getWorkByTaskAndStudentResponse(task, student));
	}

	@GetMapping(ApplicationUrls.GET_WORK_BY_TASK)
	public ResponseEntity<Work> getCurrentWork(
			@PathVariable Long id) {
		Student student = studentService.getStudent(SecurityUtils.getCurrentUser().getId());
		return ResponseEntity.ok(workService.getWorkByStudentAndworkIdResponse(id, student));
	}

}
