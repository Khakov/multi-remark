package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.work.WorkStage;
import com.kpfu.itis.khakov.multiremark.repository.work.WorkStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rustam Khakov
 */
@Service
public class WorkStageService {
	private final WorkStageRepository workStageRepository;

	@Autowired
	public WorkStageService(WorkStageRepository workStageRepository) {
		this.workStageRepository = workStageRepository;
	}

	public WorkStage getStageById(Long id) {
		return workStageRepository.findById(id).orElse(null);
	}

	public WorkStage save(WorkStage workStage) {
		return workStageRepository.save(workStage);
	}
}
