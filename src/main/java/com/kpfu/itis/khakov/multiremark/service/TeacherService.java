package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.roles.Teacher;
import com.kpfu.itis.khakov.multiremark.repository.role.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rustam Khakov
 */
@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	public Teacher getTeacherById(Long id){
		return teacherRepository.findById(id).orElse(null);
	}
}
