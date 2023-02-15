package com.adam.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.adam.dto.QMainProjectDto is a Querydsl Projection type for MainProjectDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainProjectDto extends ConstructorExpression<MainProjectDto> {

    private static final long serialVersionUID = 1215554048L;

    public QMainProjectDto(com.querydsl.core.types.Expression<Long> Id, com.querydsl.core.types.Expression<String> projectName, com.querydsl.core.types.Expression<String> imgDetail, com.querydsl.core.types.Expression<String> imgUrl) {
        super(MainProjectDto.class, new Class<?>[]{long.class, String.class, String.class, String.class}, Id, projectName, imgDetail, imgUrl);
    }

}

