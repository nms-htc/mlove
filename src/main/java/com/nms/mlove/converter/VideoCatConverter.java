/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.mlove.converter;

import com.nms.mlove.entity.VideoCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.VideoCatService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("videoCatConverter")
public class VideoCatConverter extends AbstractEntityConverter<VideoCat> {

    @EJB
    private VideoCatService service;

    @Override
    protected BaseService<VideoCat> getBaseService() {
        return service;
    }

    @Override
    protected Class<VideoCat> getEntityClass() {
        return VideoCat.class;
    }
}
