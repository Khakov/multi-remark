package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.task.TaskStage;
import com.kpfu.itis.khakov.multiremark.repository.task.TaskStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Service
public class TaskStageService implements ServiceBase<TaskStage, Long> {
	private final TaskStageRepository taskStageRepository;

	@Autowired
	public TaskStageService(TaskStageRepository taskStageRepository) {
		this.taskStageRepository = taskStageRepository;
	}

	@Override
	public TaskStage save(TaskStage taskStage) {
		return taskStageRepository.save(taskStage);
	}

	@Override
	public TaskStage get(Long id) {
		return taskStageRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(TaskStage taskStage) {
		taskStageRepository.delete(taskStage);
	}

	public List<TaskStage> findAll(){
		 taskStageRepository.deleteAll();
		 return null;
	}
}
