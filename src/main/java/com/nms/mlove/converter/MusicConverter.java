/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.mlove.converter;

import com.nms.mlove.entity.Music;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.MusicService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("musicConverter")
public class MusicConverter extends AbstractEntityConverter<Music> {

    @EJB
    private MusicService musicService;

    @Override
    protected BaseService<Music> getBaseService() {
        return musicService;
    }

    @Override
    protected Class<Music> getEntityClass() {
        return Music.class;
    }

}
