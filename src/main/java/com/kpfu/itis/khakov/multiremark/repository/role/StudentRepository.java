package com.kpfu.itis.khakov.multiremark.repository.role;

import com.kpfu.itis.khakov.multiremark.entity.roles.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rustam Khakov
 */
@Repository
public interface StudentRepository extends Neo4jRepository<Student, Long> {
	List<Student> findAll();
}
