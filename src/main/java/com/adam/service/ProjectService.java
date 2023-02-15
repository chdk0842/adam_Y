package com.adam.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adam.dto.MainProjectDto;
import com.adam.dto.ProjectFormDto;
import com.adam.dto.ProjectImgDto;
import com.adam.dto.ProjectSearchDto;
import com.adam.entity.Project;
import com.adam.entity.ProjectImg;
import com.adam.repository.ProjectImgRepository;
import com.adam.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

	private final ProjectRepository projectRepository;
	private final ProjectImgService projectImgService;
	private final ProjectImgRepository projectImgRepository;

	// 프로젝트 등록
	public Long saveProject(ProjectFormDto projectFormDto, List<MultipartFile> projectImgFileList) throws Exception {

		// 프로젝트등록
		Project project = projectFormDto.createProject();
		projectRepository.save(project);

		// 이미지 등록
		for (int i = 0; i < projectImgFileList.size(); i++) {
			ProjectImg projectImg = new ProjectImg();
			projectImg.setProject(project);

			// 첫번째 이미지 일때 대표 이미지 여부 지정
			if (i == 0) {
				projectImg.setRepimgYn("Y");
			} else {
				projectImg.setRepimgYn("N");
			}

			projectImgService.saveProjectImg(projectImg, projectImgFileList.get(i));

		}

		return project.getId();
	}

	// 프로젝트가져오기
	@Transactional(readOnly = true)
	public ProjectFormDto getProjectDtl(Long projectId) {
		List<ProjectImg> projectImgList = projectImgRepository.findByProjectIdOrderByIdAsc(projectId);
		List<ProjectImgDto> projectImgDtoList = new ArrayList<>();

		for (ProjectImg projectImg : projectImgList) {
			ProjectImgDto projectImgDto = ProjectImgDto.of(projectImg);
			projectImgDtoList.add(projectImgDto);
		}

		Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);

		ProjectFormDto projectFormDto = ProjectFormDto.of(project);

		projectFormDto.setProjectImgDtoList(projectImgDtoList);

		return projectFormDto;

	}

	// 프로젝트수정
	public Long updateProject(ProjectFormDto projectFormDto, List<MultipartFile> projectImgFileList) throws Exception {

		Project project = projectRepository.findById(projectFormDto.getId()).orElseThrow(EntityNotFoundException::new);

		project.updateProject(projectFormDto);

		List<Long> projectImgIds = projectFormDto.getProjectImgIds();

		for (int i = 0; i < projectImgFileList.size(); i++) {
			projectImgService.updateProjectImg(projectImgIds.get(i), projectImgFileList.get(i));
		}

		return project.getId();
	}

	// 프로젝트 리스트 가져오기
	@Transactional(readOnly = true)
	public Page<Project> getAdminProjectPage(ProjectSearchDto projectSearchDto, Pageable pageable) {
		return projectRepository.getAdminProjectPage(projectSearchDto, pageable);
	}

	@Transactional(readOnly = true)
	private Page<MainProjectDto> getMainProjectPage(ProjectSearchDto projectSearchDto, Pageable pageable) {
		return projectRepository.getMainProjectPage(projectSearchDto, pageable);
	}

}
