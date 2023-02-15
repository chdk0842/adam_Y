package com.adam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adam.entity.ProjectImg;

public interface ProjectImgRepository extends JpaRepository<ProjectImg, Long> {
	 List<ProjectImg> findByProjectIdOrderByIdAsc(Long projectid);
	
     ProjectImg findByProjectIdAndRepimgYn(Long projectid, String repimgYn);
}
