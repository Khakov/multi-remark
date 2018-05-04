package com.kpfu.itis.khakov.multiremark.controller;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.service.StudentService;
import com.kpfu.itis.khakov.multiremark.service.TaskService;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import com.kpfu.itis.khakov.multiremark.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rustam Khakov
 */
@Controller
public class MainController {
	private final TaskService taskService;
	private final StudentService studentService;


	@Autowired
	public MainController(TaskService taskService, StudentService studentService) {
		this.taskService = taskService;
		this.studentService = studentService;
	}

	@GetMapping(ApplicationUrls.MAIN)
	public String getMainPage(ModelMap modelMap) {
		User user = SecurityUtils.getCurrentUser();
		Role role = SecurityUtils.getUserAuthtority(user);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("tasks", taskService.getAllTasks());
		if (role == Role.ROLE_STUDENT) {
			return "student_page";
		} else {
			modelMap.addAttribute("students", studentService.getAllStudents());
			return "teacher_page";
		}
	}
}
