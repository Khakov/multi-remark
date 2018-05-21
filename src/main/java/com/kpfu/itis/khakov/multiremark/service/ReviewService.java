package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.work.Review;
import com.kpfu.itis.khakov.multiremark.repository.work.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rustam Khakov
 */
@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;

	@Autowired
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public Review addReview(Review review) {
		return reviewRepository.save(review);
	}

	public Review getReviewById(Long reviewId) {
		return reviewRepository.findById(reviewId).orElse(null);
	}

	public void save(Review review) {
		reviewRepository.save(review);
	}
}
