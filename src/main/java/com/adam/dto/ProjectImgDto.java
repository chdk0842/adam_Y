package com.adam.dto;

import org.modelmapper.ModelMapper;

import com.adam.entity.ProjectImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectImgDto {
	
     private Long id;
     
     private String imgName; //이미지 파일명
     
     private String oriImgName; //원본 이미지 파일명
     
     private String imgUrl; //이미지 조회 경로
     
     private String repimgYn; //대표 이미지 여부
     
     private static ModelMapper modelMapper = new ModelMapper();
     
     public static ProjectImgDto of(ProjectImg projectImg) {
    	 return modelMapper.map(projectImg, ProjectImgDto.class);
     }
}
