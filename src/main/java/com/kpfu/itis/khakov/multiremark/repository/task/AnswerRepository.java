package com.kpfu.itis.khakov.multiremark.repository.task;

import com.kpfu.itis.khakov.multiremark.entity.task.Answer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rustam Khakov
 */
@Repository
public interface AnswerRepository extends Neo4jRepository<Answer, Long> {
}
