package com.kpfu.itis.khakov.multiremark.repository.work;

import com.kpfu.itis.khakov.multiremark.entity.work.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rustam Khakov
 */
@Repository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {
}
