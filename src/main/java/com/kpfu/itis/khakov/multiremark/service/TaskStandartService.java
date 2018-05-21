package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.task.TaskStandart;
import com.kpfu.itis.khakov.multiremark.repository.task.TaskStandartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rustam Khakov
 */
@Service
public class TaskStandartService implements ServiceBase<TaskStandart, Long> {
	private final TaskStandartRepository taskStandartRepository;

	@Autowired
	public TaskStandartService(TaskStandartRepository taskStandartRepository) {
		this.taskStandartRepository = taskStandartRepository;
	}

	@Override
	public TaskStandart save(TaskStandart taskStandart) {
		return taskStandartRepository.save(taskStandart);
	}

	@Override
	public TaskStandart get(Long id) {
		return taskStandartRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(TaskStandart taskStandart) {
		taskStandartRepository.delete(taskStandart);
	}
}

