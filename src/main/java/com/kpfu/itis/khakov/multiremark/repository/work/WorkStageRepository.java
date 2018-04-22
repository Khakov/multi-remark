package com.kpfu.itis.khakov.multiremark.repository.work;

import com.kpfu.itis.khakov.multiremark.entity.work.WorkStage;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author Rustam Khakov
 */
public interface WorkStageRepository extends Neo4jRepository<WorkStage, Long> {
}
