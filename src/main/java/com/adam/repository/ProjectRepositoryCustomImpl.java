package com.adam.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.adam.constant.ProjectSellStatus;
import com.adam.dto.MainProjectDto;
import com.adam.dto.ProjectSearchDto;
import com.adam.dto.QMainProjectDto;
import com.adam.entity.Project;
import com.adam.entity.QProject;
import com.adam.entity.QProjectImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {
	
	private JPAQueryFactory queryFactory;
	
	public ProjectRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	public BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now();
		
		        //현재 날짜로 부터 이전 날짜를 구해준다.
				if(StringUtils.equals("all", searchDateType) || searchDateType == null)  return null;
				else if(StringUtils.equals("1d", searchDateType)) dateTime = dateTime.minusDays(1); 
				else if(StringUtils.equals("1w", searchDateType)) dateTime = dateTime.minusWeeks(1);
				else if(StringUtils.equals("1m", searchDateType)) dateTime = dateTime.minusMonths(1);
				else if(StringUtils.equals("6m", searchDateType)) dateTime = dateTime.minusMonths(6);
				
				return QProject.project.regTime.after(dateTime);//이후시간
	}
	
	private BooleanExpression searchSellStatusEq(ProjectSellStatus searchSellStatus) {
		return searchSellStatus == null ? null : QProject.project.projectSellStatus.eq(searchSellStatus);
	}
	
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("projectName", searchBy)) {
			return QProject.project.projectName.like("%" + searchQuery + "%"); //projectName LIKE %프로젝트%
		} else if(StringUtils.equals("createdBy", searchBy)) {
			return QProject.project.createdBy.like("%" + searchQuery + "%"); //createdBy LIKE %test.com%
		}
		
		return null;
	}
	
	@Override
	public Page<Project> getAdminProjectPage(ProjectSearchDto projectSearchDto, Pageable pageable) {
		List<Project> content = queryFactory
				.selectFrom(QProject.project) //select * from project
				.where(regDtsAfter(projectSearchDto.getSearchDateType()), // where reg_time = ?
					   searchSellStatusEq(projectSearchDto.getSearchSellStatus()), //and sell_status = ?
					   searchByLike(projectSearchDto.getSearchBy(), projectSearchDto.getSearchQuery())) // and projectName LIKE %검색어%
				.orderBy(QProject.project.id.desc())
				.offset(pageable.getOffset()) //데이터를 가져올 시작 index
				.limit(pageable.getPageSize()) //한번에 가지고 올 최대 개수
				.fetch();
				
		long total = queryFactory.select(Wildcard.count).from(QProject.project)
                .where(regDtsAfter(projectSearchDto.getSearchDateType()),
                        searchSellStatusEq(projectSearchDto.getSearchSellStatus()),
                        searchByLike(projectSearchDto.getSearchBy(), projectSearchDto.getSearchQuery()))
                .fetchOne();
		
		
		return new PageImpl<>(content, pageable, total);
	}
	 private BooleanExpression projectNameLike(String searchQuery){
	        return StringUtils.isEmpty(searchQuery) ? null : QProject.project.projectName.like("%" + searchQuery + "%");
	    }
	 
	 @Override
		public Page<MainProjectDto> getMainProjectPage(ProjectSearchDto projectSearchDto, Pageable pageable) {
			 QProject project = QProject.project;
		        QProjectImg projectImg = QProjectImg.projectImg;

		        List<MainProjectDto> content = queryFactory
		                .select(
		                        new QMainProjectDto(
		                        		project.id,
		                        		project.projectName,
		                        		project.projectContent,
		                        		projectImg.imgUrl)
		                )
		                .from(projectImg)
		                .join(projectImg.project, project)
		                .where(projectImg.repimgYn.eq("Y"))
		                .where(projectNameLike(projectSearchDto.getSearchQuery()))
		                .orderBy(project.id.desc())
		                .offset(pageable.getOffset())
		                .limit(pageable.getPageSize())
		                .fetch();

		        long total = queryFactory
		                .select(Wildcard.count)
		                .from(projectImg)
		                .join(projectImg.project, project)
		                .where(projectImg.repimgYn.eq("Y"))
		                .where(projectNameLike(projectSearchDto.getSearchQuery()))
		                .fetchOne()
		                ;

		        return new PageImpl<>(content, pageable, total);
		}
	 
	
}
