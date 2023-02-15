package com.adam.entity;

import java.sql.Date;

import javax.persistence.*;

import com.adam.constant.ProjectSellStatus;
import com.adam.dto.ProjectFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "project") // 테이블명
@Getter
@Setter
@ToString
public class Project extends BaseEntity {

	@Id
	@Column(name = "project_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 프로젝트코드

	@Column(nullable = false, length = 16)
	private String projectName; // 프로젝트명

	private String gitLink; // 깃링크

	private String projectDate; // 기간

	@Column(nullable = false)
	private Date deadLine; // 데드라인

	@Column(nullable = false)
	private String projectContent; // 설명

	@Enumerated(EnumType.STRING)
	private ProjectSellStatus projectSellStatus; // 프로젝트 완료 상태

	public void updateProject(ProjectFormDto projectFormDto) {
		this.projectName = projectFormDto.getProjectName();
		this.gitLink = projectFormDto.getGitLink();
		this.projectDate = projectFormDto.getProjectDate();
		this.deadLine = projectFormDto.getDeadLine();
		this.projectContent = projectFormDto.getProjectContent();
		this.projectSellStatus = projectFormDto.getProjectSellStatus();
	}

}
