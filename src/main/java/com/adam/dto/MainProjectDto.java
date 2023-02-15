package com.adam.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainProjectDto {
	private Long Id;

	private String projectName;

	private String imgDetail;

	private String imgUrl;

	@QueryProjection //쿼리 dsl로 결과 조회시 MainProjectDto 객체로 바로 받아올 수 있다.
	   public MainProjectDto(Long Id, String projectName, String imgDetail, String imgUrl) {
		   this.Id = Id;
		   this.projectName = projectName;
		   this.imgDetail = imgDetail;
		   this.imgUrl = imgUrl;
	}
}
