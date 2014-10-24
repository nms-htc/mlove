/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.Cat;
import com.nms.mlove.entity.Cat_;
import com.nms.mlove.entity.Product_;
import com.nms.mlove.service.CatService;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MinhDT
 * @param <T>
 */
public abstract class CatServiceBean<T extends Cat> extends AbstractService<T> implements CatService<T> {

    private static final long serialVersionUID = 3906692967558682211L;

    public CatServiceBean(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public T findByTitle(String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.where(cb.equal(root.get(Cat_.title), title));

        TypedQuery<T> q = em.createQuery(cq);
        return q.getSingleResult();
    }

    @Override
    protected void onBeforePersist(T entity) {
        super.onBeforePersist(entity); //To change body of generated methods, choose Tools | Templates.
        
        T checkCode = null;
        try {
            checkCode = findByTitle(entity.getTitle());

            if (checkCode != null) {
                throw new EJBException("title-dupplicate");
            }
        } catch (NoResultException e) {
            // OK
        }
    }

    @Override
    protected void onBeforeUpdate(T entity) {
        super.onBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
        
        T checkCode = null;
        try {
            checkCode = findByTitle(entity.getTitle());

            if (checkCode != null && checkCode.getId().compareTo(entity.getId()) != 0) {
                throw new EJBException("title-dupplicate");
            }
        } catch (NoResultException e) {
            // OK
        }
    }
}
