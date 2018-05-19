package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.task.TestCase;
import com.kpfu.itis.khakov.multiremark.repository.task.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rustam Khakov
 */
@Service
public class TestCaseService implements ServiceBase<TestCase, Long>{
	private final TestRepository testRepository;

	@Autowired
	public TestCaseService(TestRepository testRepository) {
		this.testRepository = testRepository;
	}

	@Override
	public TestCase save(TestCase testCase) {
		return testRepository.save(testCase);
	}

	@Override
	public TestCase get(Long id) {
		return testRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(TestCase testCase) {
		testRepository.delete(testCase);
	}
}
