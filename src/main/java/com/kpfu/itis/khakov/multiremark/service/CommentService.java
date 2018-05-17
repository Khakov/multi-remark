package com.kpfu.itis.khakov.multiremark.service;

import com.kpfu.itis.khakov.multiremark.entity.work.Comment;
import com.kpfu.itis.khakov.multiremark.repository.work.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rustam Khakov
 */
@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;

	public Comment get(Long id) {
		return commentRepository.findById(id).orElse(null);
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
}
