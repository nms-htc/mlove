/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.service;

import com.nms.mlove.entity.Post;
import com.nms.mlove.entity.PostCat;
import java.util.List;

/**
 *
 * @author MinhDT
 */
public interface PostCatService extends CatService<PostCat>{
    
    public List<Post> findItemUsingCat(long id);
}
