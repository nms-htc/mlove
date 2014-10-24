/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.mlove.controller.convertor;

import com.nms.mlove.entity.VideoCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.VideoCatService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author NamTA
 */
@FacesConverter("videoCatConvertor")
public class VideoCatConvertor extends AbstractEntityConvertor<VideoCat>
{
    @EJB
    private VideoCatService service;
            
    @Override
    protected BaseService<VideoCat> getBaseService()
    {
        return service;
    }

    @Override
    protected Class<VideoCat> getEntityClass()
    {
        return VideoCat.class;
    }
}
