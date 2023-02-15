package com.adam.dto;

import com.adam.constant.ProjectSellStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectSearchDto {
	      private String searchDateType;
	      private ProjectSellStatus searchSellStatus;
	      private String searchBy;
	      private String searchQuery = "";
}
