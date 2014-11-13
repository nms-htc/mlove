/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.mlove.converter;

import com.nms.mlove.entity.PostCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.PostCatService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("postCatConverter")
public class PostCatConverter extends AbstractEntityConverter<PostCat> {

    @EJB
    private PostCatService service;

    @Override
    protected BaseService<PostCat> getBaseService() {
        return service;
    }

    @Override
    protected Class<PostCat> getEntityClass() {
        return PostCat.class;
    }

}
