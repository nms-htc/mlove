/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.controller.convertor;

import com.nms.mlove.entity.MusicCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.MusicCatService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author NamTA
 */
@FacesConverter("musicCatConvertor")
public class MusicCatConvertor extends AbstractEntityConvertor<MusicCat>
{

    @EJB private MusicCatService catService;

    @Override
    protected BaseService<MusicCat> getBaseService()
    {
        return catService;
    }

    @Override
    protected Class<MusicCat> getEntityClass()
    {
        return MusicCat.class;
    }

}
