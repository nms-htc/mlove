package com.nms.mlove.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-24T16:50:08")
@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ { 

    public static volatile SingularAttribute<BaseEntity, Date> createdDate;
    public static volatile SingularAttribute<BaseEntity, Date> modifiedDate;
    public static volatile SingularAttribute<BaseEntity, String> description;
    public static volatile SingularAttribute<BaseEntity, Long> id;

}