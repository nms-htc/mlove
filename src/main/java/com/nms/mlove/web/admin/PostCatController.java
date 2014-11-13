/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.web.admin;

import com.nms.mlove.entity.PostCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.PostCatService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MinhDT
 */
@Named
@ViewScoped
public class PostCatController extends AbstractManagedBean<PostCat> {

    private static final long serialVersionUID = 6308589831445354415L;

    @EJB
    private PostCatService service;

    @Override
    protected BaseService<PostCat> getBaseService() {
        return service;
    }

    @Override
    protected PostCat initEntity() {
        return new PostCat();
    }

}
