package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.repository.role.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Service
public class StudentService {
	private final PasswordEncoder passwordEncoder;
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
		this.studentRepository = studentRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student createStudent(Student student) {
		student.setRole(Role.ROLE_STUDENT);
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		return studentRepository.save(student);
	}
}
