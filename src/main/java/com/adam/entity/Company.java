package com.adam.entity;

import javax.persistence.*;

import com.adam.dto.AdminFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@TableGenerator(
        name = "COMPANY_SEQ_GENERATOR",
        table = "MY_SEQUENCE",
        pkColumnValue = "COMPANY_SEQ",
        initialValue = 10001,
        allocationSize = 1 
)
public class Company{
	
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.TABLE,
		    generator = "COMPANY_SEQ_GENERATOR")
	private Long comNum; //회사코드

	private String comName; //회사명


	public static Company createCompany(AdminFormDto adminFormDto) {
		Company company = new Company();
		company.setComName(adminFormDto.getComName());
		
		
		return company;
	}
	
}
