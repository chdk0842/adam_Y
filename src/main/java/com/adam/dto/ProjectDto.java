package com.adam.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import com.adam.constant.ProjectSellStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
     private Long id; //프로젝트코드
     
     private String projectName; //프로젝트명
     
     private String projectContent; //설명
       
     private Date deadLine; //데드라인
     
     private String projectDate; //기간
     
     private String gitLink; //링크
     
     private ProjectSellStatus projectSellStatus;
     
     private LocalDateTime regTime; //등록시간
     
     private LocalDateTime upDateTime; //수정시간
}
