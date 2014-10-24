/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.BaseEntity_;
import com.nms.mlove.entity.Cat;
import com.nms.mlove.entity.Post;
import com.nms.mlove.entity.Product;
import com.nms.mlove.entity.Product_;
import com.nms.mlove.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
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

    @Override
    protected List<Predicate> buildConditions(T criteria, Map<String, Object> filters, Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria != null) {
            if (criteria.getCreatedDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(Product_.createdDate), criteria.getCreatedDate()));
            }

            if (criteria.getTitle() != null && !criteria.getTitle().trim().isEmpty()) {
                predicates.add(cb.like(cb.upper(root.get(Product_.title)), "%" + criteria.getTitle().trim().toUpperCase() + "%"));
            }
            
            if (criteria.getCat() != null) {
                predicates.add(cb.equal(root.get(Product_.cat),criteria.getCat()));
            }
        }
        return predicates;
    }

    @Override
    public List<T> findByCat(Cat cat) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.where(root.get(Product_.cat).in(cat));
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }

    
}
