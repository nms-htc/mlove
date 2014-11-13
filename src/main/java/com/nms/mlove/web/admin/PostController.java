/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.web.admin;

import com.nms.mlove.entity.Post;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.PostService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MinhDT
 */
@Named
@ViewScoped
public class PostController extends AbstractManagedBean<Post> {

    private static final long serialVersionUID = -2212751512634671275L;

    @EJB
    private PostService service;

    @Override
    protected BaseService<Post> getBaseService() {
        return service;
    }

    @Override
    protected Post initEntity() {
        return new Post();
    }

}
