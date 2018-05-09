package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkAnswer;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Service
public class WorkService {
	private final WorkRepository workRepository;

	@Autowired
	public WorkService(WorkRepository workRepository) {
		this.workRepository = workRepository;
	}

	public void createWork(Student student, Task task, Answer answer, String text) {
		Work work = new Work();
		work.setTask(task);
		work.setStudent(student);
		if (answer != null) {
			work.setAnswer(answer);
		} else {
			work.setWorkAnswer(new WorkAnswer(text));
		}
		workRepository.save(work);
	}

	public List<Work> getWorksByStudent(Student student) {
		return workRepository.findAllByStudent(student);
	}
}
