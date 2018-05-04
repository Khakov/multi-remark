package com.kpfu.itis.khakov.multiremark.controller;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.Teacher;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.repository.role.TeacherRepository;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkRepository;
//import com.kpfu.itis.khakov.multiremark.repository.work.WorkStatusRelationshipRepository;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkStageRepository;
import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rustam Khakov
 */
@Controller
public class WorkController {
	@Autowired
	WorkRepository workRepository;
/*	@Autowired
	WorkStatusRelationshipRepository workStatusRelationshipRepository;*/
	@Autowired
	WorkStageRepository workStatusRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping(ApplicationUrls.WORK)
	public String getWork(ModelMap models) {
		/*Work work = workRepository.findAll().get(0);
*//*		Student student = new Student();
		student.setName("First Student");
		Task task = new Task();
		task.setName("Create Db");
		WorkStatus status = new WorkStatus();
		status.setName(WorkState.PENDING.name());
		WorkStatus status1 = new WorkStatus();
		status1.setName(WorkState.PEER_REVIEW.name());
		WorkStatusRelationship relationship = new WorkStatusRelationship();
		relationship.setPreviousWorkStatus(status);
		List<TaskType> typeList = new ArrayList();
		typeList.add(TaskType.PROJECT);
		relationship.setTaskTypes(typeList);
		relationship.setNextWorkStatus(status1);
		work1.setStudent(student);
		work1.setTask(task);
		work1.setName("realize");
		work1.setStatus(status);
		workStatusRelationshipRepository.save(relationship);*//*
//		workRepository.save(work1);

//		models.addAttribute("work", work1);
		*//*Optional<WorkStatus> workStatus = workStatusRepository.findById(81L);
		work1.setStatus(workStatus.orElseGet(WorkStatus::new));
		workRepository.save(work1);
		List<WorkStatusRelationship> workStatusRelationships = workStatusRelationshipRepository.findAllByWorkTypesIn(TaskType.PROJECT);*//*
		*//*work.setName("Simple work");
		Student student = new Student();
		student.setLogin("login");
		student.setName("Arthur");
		work.setStudent(student);
		Task task = new Task();
		Teacher teacher = new Teacher();
		teacher.setLogin("teacher");
		teacher.setName("Teacher 1");
		teacher.setRole(Role.ROLE_ADMIN);
		if (teacher.getStudents() == null) {
			teacher.setStudents(Arrays.asList(student));
		} else {
			teacher.getStudents().add(student);
		}
		task.setTeacher(teacher);
		work.setTask(task);
		*//*
*//*		work.getTask().setName("Simple code task");
		TaskStage stage = new TaskStage();
		WorkStage workStage = new WorkStage();
		workStage.setWork(work);
		stage.setStages(Arrays.asList(workStage));
		WorkType workType = new WorkType();
		workType.setType(TaskType.CODE);
		workType.setStages(Arrays.asList(stage));
		work.getTask().setWorkType(workType);
		workRepository.save(work);*//*


		Teacher teacher = teacherRepository.findAll().get(0);
		teacher.setRole(Role.ROLE_TEACHER);
		*//*teacher.setEmail("teacher@teacher.com");
		teacher.setPassword(passwordEncoder.encode("password"));*//*
		teacherRepository.save(teacher);*/
		return "work";
	}
}
