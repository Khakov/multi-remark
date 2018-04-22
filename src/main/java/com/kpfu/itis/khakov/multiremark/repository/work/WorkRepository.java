package com.kpfu.itis.khakov.multiremark.repository.work;

import com.kpfu.itis.khakov.multiremark.entity.work.Work;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Repository
public interface WorkRepository extends Neo4jRepository<Work, Long> {
	List<Work> findAll();
}
