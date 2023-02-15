package com.adam.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;

import org.modelmapper.ModelMapper;

import com.adam.constant.ProjectSellStatus;
import com.adam.entity.Project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectFormDto {
	
       private Long id; //프로젝트코드
       
       @NotBlank(message = "프로젝트명은 필수 입력 값입니다.")
       private String projectName; //프로젝트명
       
       @NotNull(message = "설명은 필수 입력 값입니다.")
       private String projectContent; //설명
         
       @NotNull(message = "데드라인은 필수 입력 값입니다.")
       private Date deadLine; //데드라인
       
       private String projectDate; //기간
       
       private String gitLink; //링크
       
       private ProjectSellStatus projectSellStatus; //프로젝트 상태
       
       private List<ProjectImgDto> projectImgDtoList = new ArrayList<>(); //이미지 정보를 저장하는 리스트
       
       private List<Long> projectImgIds = new ArrayList<>(); //이미지 아이디 저장 -> 수정시 담아둘용도
       
       private static ModelMapper modelMapper = new ModelMapper();
       
       public Project createProject() {
    	   return modelMapper.map(this, Project.class);
       }
       
       public static ProjectFormDto of(Project project) {
    	   return modelMapper.map(project, ProjectFormDto.class);
       }

       
       
       
}
