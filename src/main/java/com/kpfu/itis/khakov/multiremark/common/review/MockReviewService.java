package com.kpfu.itis.khakov.multiremark.common.review;

import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;

/**
 * @author Rustam Khakov
 */
public class MockReviewService implements ReviewService {
	@Override
	public String reviewWork(Work work, User user) {
		return null;
	}
}
