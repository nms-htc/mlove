/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.PostCat;
import com.nms.mlove.service.PostCatService;
import javax.ejb.Stateless;

/**
 *
 * @author MinhDT
 */
@Stateless
public class PostCatServiceBean extends CatServiceBean<PostCat> implements PostCatService {

    private static final long serialVersionUID = -4144069309340411787L;

    public PostCatServiceBean() {
        super(PostCat.class);
    }

}
