/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.BaseEntity_;
import com.nms.mlove.entity.Post;
import com.nms.mlove.entity.PostCat;
import com.nms.mlove.entity.Post_;
import com.nms.mlove.entity.Product_;
import com.nms.mlove.service.PostCatService;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

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

//    @Override
//    protected void onBeforeRemove(PostCat entity) {
//        super.onBeforeRemove(entity);
//        
//        List<Post> listItem = null;
//        try {
//            listItem = findItemUsingCat(entity.getId());
//
//            if (listItem != null && !listItem.isEmpty()) {
//                throw new EJBException("category-in-used");
//            }
//        } catch (NoResultException e) {
//            // OK
//        }
//    }

}
