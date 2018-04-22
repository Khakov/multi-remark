package com.kpfu.itis.khakov.multiremark.repository.work;

import com.kpfu.itis.khakov.multiremark.entity.work.Result;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rustam Khakov
 */
@Repository
public interface ResultRepository extends Neo4jRepository<Result, Long> {
}
