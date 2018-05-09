package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.task.Question;
import com.kpfu.itis.khakov.multiremark.repository.task.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Service
public class QuestionService {
	private final QuestionRepository questionRepository;

	@Autowired
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	public Question createQuestion(Question question) {
		return questionRepository.save(question);
	}

	public List<Question> getAllQuestion() {
		return questionRepository.getAll();
	}

}
