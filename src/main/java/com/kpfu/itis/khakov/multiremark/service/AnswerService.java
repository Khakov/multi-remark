package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import com.kpfu.itis.khakov.multiremark.repository.task.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rustam Khakov
 */
@Service
public class AnswerService {
	private final AnswerRepository answerRepository;

	@Autowired
	public AnswerService(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}

	public Answer getAnswerById(Long id) {
		return answerRepository.findById(id).orElse(null);
	}
}
