package com.kpfu.itis.khakov.multiremark.repository.work;

import com.kpfu.itis.khakov.multiremark.entity.work.Review;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rustam Khakov
 */
@Repository
public interface ReviewRepository extends Neo4jRepository<Review, Long> {
}
