package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.task.Task;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkType;
import com.kpfu.itis.khakov.multiremark.repository.task.TaskRepository;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Service
public class TaskService {

	private final TaskRepository taskRepository;

	private final WorkTypeRepository workTypeRepository;

	@Autowired
	public TaskService(TaskRepository taskRepository, WorkTypeRepository workTypeRepository) {
		this.taskRepository = taskRepository;
		this.workTypeRepository = workTypeRepository;
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public List<Task> getTasksByUser(){
		return null;
	}

	public Task getTaskById(Long taskId) {
		return taskRepository.findById(taskId).orElse(null);
	}

	public Task createTask(Task task) {
		WorkType workType;
		switch (task.getWorkType().getType()) {
			case CODE:
			case TEST:
			case PROJECT:
			case TEXT:
				workType = workTypeRepository.findFirstByType(task.getWorkType().getType());
				break;
			default:
				workType = task.getWorkType();
				break;
		}
		task.setWorkType(workType);
		taskRepository.save(task);
		return taskRepository.save(task);
	}
}
