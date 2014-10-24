/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.Post;
import com.nms.mlove.service.PostService;
import javax.ejb.Stateless;

/**
 *
 * @author MinhDT
 */
@Stateless
public class PostServiceBean extends ProductServiceBean<Post> implements PostService {

    private static final long serialVersionUID = -5611552882128277266L;

    public PostServiceBean() {
        super(Post.class);
    }

}
