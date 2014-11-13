/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.mlove.converter;

import com.nms.mlove.entity.MusicCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.MusicCatService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("musicCatConverter")
public class MusicCatConverter extends AbstractEntityConverter<MusicCat> {

    @EJB
    private MusicCatService catService;

    @Override
    protected BaseService<MusicCat> getBaseService() {
        return catService;
    }

    @Override
    protected Class<MusicCat> getEntityClass() {
        return MusicCat.class;
    }

}
