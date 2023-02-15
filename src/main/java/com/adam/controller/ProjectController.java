package com.adam.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adam.dto.ProjectFormDto;
import com.adam.dto.ProjectSearchDto;
import com.adam.entity.Project;
import com.adam.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class ProjectController {
	private final ProjectService projectService;

	// 기획하기 페이지
	@GetMapping(value = "/project/new")
	public String projectForm(Model model) {
		model.addAttribute("projectFormDto", new ProjectFormDto());
		return "project/projectForm";

	}

	// 프로젝트 등록
	@PostMapping(value = "/project/new")
	public String projectNew(@Valid ProjectFormDto projectFormDto, BindingResult bindingResult, Model model,
			@RequestParam("projectImgFile") List<MultipartFile> projectImgFileList) {

		if (bindingResult.hasErrors()) {
			return "project/projectForm";
		}

		// 첫번째 이미지가 있는지 검사
		if (projectImgFileList.get(0).isEmpty() && projectFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값 입니다.");
			return "project/projectForm";
		}

		try {
			projectService.saveProject(projectFormDto, projectImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "프로젝트 등록 중 에러가 발생했습니다.");
			return "project/projectForm";
		}

		return "/member/userMain";
	}

	// 프로젝트 수정 페이지 보기
	@GetMapping(value = "/project/{projectId}")
	public String projectDtl(@PathVariable("projectId") Long projectId, Model model) {
		try {
			ProjectFormDto projectFormDto = projectService.getProjectDtl(projectId);
			model.addAttribute(projectFormDto);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "존재하지 않는 프로젝트입니다.");
			model.addAttribute("projectFormDto", new ProjectFormDto());
			return "project/projectForm";
		}
		return "project/projectForm";
	}

	// 프로젝트 수정
	@PostMapping(value = "/project/{projectId}")
	public String projectUpdate(@Valid ProjectFormDto projectFormDto, BindingResult bindingResult, Model model,
			@RequestParam("projectImgFile") List<MultipartFile> projectImgFileList) {
		if (bindingResult.hasErrors()) {
			return "project/projectForm";
		}

		// 첫번째 이미지가 있는지 검사
		if (projectImgFileList.get(0).isEmpty() && projectFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 이미지는 필수 입력 값 입니다.");
			return "project/projectForm";
		}

		try {
			projectService.updateProject(projectFormDto, projectImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "프로젝트 수정 중 에러가 발생했습니다.");
			return "project/projectForm";
		}

		return "/member/userMain";
	}
	
	//프로젝트 페이지
	@GetMapping(value = {"/projects", "/projects/{page}"}) //페이지 번호가 없는 경우와 있는 경우 2가지를 매핑
	public String projectManage(ProjectSearchDto projectSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
		
		//url경로에 페이지가 있으면 해당 페이지를 조회하도록 하고 페이지 번호가 없으면 0페이지를 조회하도록 한다.
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3); //of(조회할 페이지의 번호, 한페이지당 조회할 데이터의 갯수)
		
		Page<Project> projects = projectService.getAdminProjectPage(projectSearchDto, pageable);
		
		model.addAttribute("projects", projects);
		model.addAttribute("projectSearchDto", projectSearchDto);
		model.addAttribute("maxPage", 5); //상품 관리 메뉴 하단에 보여줄 최대 페이지 번호
		
		return "project/projectMng";

	}

}
