/*
package com.kpfu.itis.khakov.multiremark.repository.work;

import com.kpfu.itis.khakov.multiremark.entity.relationships.WorkStatusRelationship;
import com.kpfu.itis.khakov.multiremark.entity.type.TaskType;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

*/
/**
 * @author Rustam Khakov
 *//*

public interface WorkStatusRelationshipRepository extends Neo4jRepository<WorkStatusRelationship, Long> {
	@Query(value = "MATCH (n)-[r0:`NEXT_STATUS`]->(m) WHERE {work_type} in r0.`workTypes` RETURN n,r0,m")
	List<WorkStatusRelationship> findAllByWorkTypesIn(@Param("work_type") TaskType type);
}
*/
