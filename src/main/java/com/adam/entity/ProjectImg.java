package com.adam.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="project_img") //테이블명
@Getter
@Setter
public class ProjectImg extends BaseEntity{

	@Id
	@Column(name = "project_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imgName; //이미지 파일명
	
    private String oriImgName; //원본 이미지 파일명
	
	private String imgUrl; //이미지 조회 경로
	
	private String repimgYn; //대표 이미지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project; //프로젝트명
	
	//원본이미지 파일명, 업데이트할 이미지 파일명, 이미지 경로를 파라메터로 받아서 이미지 정보를 업데이트 하는 메소드.
		public void updateProjectImg(String oriImgName, String imgName, String imgUrl) {
			this.oriImgName = oriImgName;
			this.imgName = imgName;
			this.imgUrl = imgUrl;
		}
	

	
}
