package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestTaskController {

	@PostMapping(ApplicationUrls.CREATE_TASK)
	public ResponseEntity<Task> createTask(@RequestBody Task task){
		return null;
	}
}
