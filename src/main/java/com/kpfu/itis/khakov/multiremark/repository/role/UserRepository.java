package com.kpfu.itis.khakov.multiremark.repository.role;

import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rustam Khakov
 */
@Repository
public interface UserRepository  extends Neo4jRepository<User, Long> {
	User findByEmail(String email);
}
