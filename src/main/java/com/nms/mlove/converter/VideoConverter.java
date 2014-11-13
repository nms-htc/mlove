/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.mlove.converter;

import com.nms.mlove.entity.Video;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.VideoService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("videoConverter")
public class VideoConverter extends AbstractEntityConverter<Video> {

    @EJB
    private VideoService videoService;

    @Override
    protected BaseService<Video> getBaseService() {
        return videoService;
    }

    @Override
    protected Class<Video> getEntityClass() {
        return Video.class;
    }

}
