package com.nms.mlove.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-09T11:15:43")
@StaticMetamodel(BaseProduct.class)
public abstract class BaseProduct_ extends BaseEntity_ {

    public static volatile SingularAttribute<BaseProduct, String> title;
    public static volatile SingularAttribute<BaseProduct, Double> price;
    public static volatile SingularAttribute<BaseProduct, Boolean> promoPrice;
    public static volatile SingularAttribute<BaseProduct, Integer> downCount;
    public static volatile SingularAttribute<BaseProduct, Boolean> promotion;
    public static volatile SingularAttribute<BaseProduct, Integer> viewCount;

}