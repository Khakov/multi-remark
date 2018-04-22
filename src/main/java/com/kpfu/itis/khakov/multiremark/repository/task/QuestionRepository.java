package com.kpfu.itis.khakov.multiremark.repository.task;

import com.kpfu.itis.khakov.multiremark.entity.task.Question;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rustam Khakov
 */
@Repository
public interface QuestionRepository extends Neo4jRepository<Question, Long> {
}
