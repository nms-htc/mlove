/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.Product;
import com.nms.mlove.entity.Product_;
import com.nms.mlove.service.ProductService;
import javax.ejb.EJBException;
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
public abstract class ProductServiceBean<T extends Product> extends AbstractService<T> implements ProductService<T> {

    private static final long serialVersionUID = 1544559586143371219L;

    private Class<T> entityClass;
    
    public ProductServiceBean(Class<T> entityClass) {
        super(entityClass);
        setProductClass(entityClass);
    }
    
    private void setProductClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T findByTitle(String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.where(cb.equal(root.get(Product_.title), title));

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
