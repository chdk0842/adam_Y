package com.adam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adam.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	Company findByComNum(Long comNum);
}
