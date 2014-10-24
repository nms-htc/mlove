/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.controller.convertor;

import com.nms.mlove.entity.PostCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.PostCatService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author MinhDT
 */
@FacesConverter("postCatConvertor")
public class PostCatConvertor extends AbstractEntityConvertor<PostCat> {

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
