package com.adam.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectImg is a Querydsl query type for ProjectImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectImg extends EntityPathBase<ProjectImg> {

    private static final long serialVersionUID = -906351773L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectImg projectImg = new QProjectImg("projectImg");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath imgUrl = createString("imgUrl");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath oriImgName = createString("oriImgName");

    public final QProject project;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final StringPath repimgYn = createString("repimgYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> upDateTime = _super.upDateTime;

    public QProjectImg(String variable) {
        this(ProjectImg.class, forVariable(variable), INITS);
    }

    public QProjectImg(Path<? extends ProjectImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectImg(PathMetadata metadata, PathInits inits) {
        this(ProjectImg.class, metadata, inits);
    }

    public QProjectImg(Class<? extends ProjectImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

