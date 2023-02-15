package com.adam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.adam.dto.MainProjectDto;
import com.adam.dto.ProjectSearchDto;
import com.adam.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>,
QuerydslPredicateExecutor<Project>, ProjectRepositoryCustom {
    
	List<Project> findByProjectName(String projectName);
	
	List<Project> findByProjectNameOrProjectContent(String projectName, String projectContent);

	
//내가 작성	@Query("select p from Project p where p.projectContent like %?1% order by p.deadLine desc")
//	List<Project> findByProjectContent(String projectContent);
	
	
	//@Query(value = "select * from Project p where p.projectContent like %:projectContent% order by p.deadLine desc", nativeQuery =true)
	//List<Project> findByProjectContentByNative(@Param("projectContent") String projectContent);
}
