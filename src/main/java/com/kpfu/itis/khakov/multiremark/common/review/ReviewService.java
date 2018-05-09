package com.kpfu.itis.khakov.multiremark.common.review;

import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.entity.work.Work;

/**
 * @author Rustam Khakov
 */
public interface ReviewService {
	String reviewWork(Work work, User user);
}
