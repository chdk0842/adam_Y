package com.adam.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


import com.adam.entity.ProjectImg;
import com.adam.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectImgService {

	@Value("${projectImgLocation}")
	private String projectImgLocation;
	
	private final ProjectImgRepository projectImgRepository;
	
	private final FileService fileService;
	
	//이미지 저장 메소드
	public void saveProjectImg(ProjectImg projectImg, MultipartFile projectImgFile) throws Exception {
		String oriImgName = projectImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		//파일 업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(projectImgLocation, oriImgName, projectImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
		}
		
		//이미지 정보 저장
		projectImg.updateProjectImg(oriImgName, imgName, imgUrl);
		projectImgRepository.save(projectImg);
	}
	
	//이미지 업데이트 메소드
	public void updateProjectImg(Long projectImgId, MultipartFile projectImgFile) throws Exception {
		if(!projectImgFile.isEmpty()) {
			ProjectImg savedProjectImg = projectImgRepository.findById(projectImgId).orElseThrow(EntityNotFoundException::new);
			
			//기존 이미지 파일 삭제
			if(!StringUtils.isEmpty(savedProjectImg.getImgName())) {
				fileService.deleteFile(projectImgLocation + "/" + savedProjectImg.getImgName());
			}
			
			//수정된 이미지 파일 업로드
			String oriImgName = projectImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile(projectImgLocation, oriImgName, projectImgFile.getBytes());
			String imgUrl = "/images/item/" + imgName;
			
			savedProjectImg.updateProjectImg(oriImgName, imgName, imgUrl);
		}
	}
	
	
	
}
