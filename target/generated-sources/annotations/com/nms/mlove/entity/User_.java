package com.nms.mlove.entity;

import com.nms.mlove.entity.User.Group;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-24T10:30:09")
@StaticMetamodel(User.class)
public class User_ extends BaseEntity_ {

    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> fullname;
    public static volatile ListAttribute<User, Group> groups;

}