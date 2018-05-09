package com.kpfu.itis.khakov.multiremark.repository.work;

import com.kpfu.itis.khakov.multiremark.entity.type.TaskType;
import com.kpfu.itis.khakov.multiremark.entity.work.WorkType;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rustam Khakov
 */
@Repository
public interface WorkTypeRepository extends Neo4jRepository<WorkType, Long> {
	WorkType findFirstByType(TaskType taskType);
}
