package com.kpfu.itis.khakov.multiremark.controller.rest;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.service.StudentService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import com.kpfu.itis.khakov.multiremark.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@RestController
public class RestStudentController {
	private final StudentService studentService;


	@Autowired
	public RestStudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping(ApplicationUrls.GET_STUDENTS)
	public ResponseEntity<List<Student>> getStudents() {
		User user = SecurityUtils.getCurrentUser();
		Role role = SecurityUtils.getUserAuthtority(user);
		if (role == Role.ROLE_STUDENT) {
			return ResponseEntity.ok(studentService.getAllStudents());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build();
	}
}
