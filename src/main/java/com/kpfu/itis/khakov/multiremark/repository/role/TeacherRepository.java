package com.kpfu.itis.khakov.multiremark.repository.role;

import com.kpfu.itis.khakov.multiremark.entity.roles.Teacher;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Repository
public interface TeacherRepository extends Neo4jRepository<Teacher, Long> {
	List<Teacher> findAll();
}
