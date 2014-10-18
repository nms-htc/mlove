package com.nms.mlove.ejb;

import com.nms.mlove.entity.BaseEntity;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.util.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

/**
 * @author Nguyen Trong Cuong
 * @param <T>
 * @since 09/10/2014
 * @version 1.0
 */
public abstract class AbstractService<T extends BaseEntity> implements BaseService<T> {

    private static final long serialVersionUID = 4127686125157546489L;

    @PersistenceContext
    protected EntityManager em;
    
    private final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T find(long id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public int countAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    /* Callback method */
    protected void onBeforePersist(T entity) {
    }

    /* Callback method */
    protected void onAfterPersist(T entity) {
    }

    @Override
    public T persist(T entity) {
        onBeforePersist(entity);
        em.persist(entity);
        em.flush();
        onAfterPersist(entity);
        return entity;
    }

    /* Callback method */
    protected void onBeforeUpdate(T entity) {
    }

    /* Callback method */
    protected void onAfterUpdate(T entity) {
    }

    @Override
    public T update(T entity) {
        onBeforeUpdate(entity);
        em.merge(entity);
        onAfterUpdate(entity);
        return entity;
    }

    /* Callback method */
    protected void onBeforeRemove(T entity) {
    }

    /* Callback method */
    protected void onAfterRemove(T entity) {
    }

    @Override
    public void remove(T entity) {
        onBeforeRemove(entity);
        em.remove(em.merge(entity));
        onAfterRemove(entity);
    }

    @Override
    public int countForPFDatatable(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    @Override
    public List<T> searchForPFDatatable(int start, int range, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        Order order = buildOrderBy(sortField, sortOrder, cb, root);

        if (order != null) {
            cq.orderBy(order);
        }

        TypedQuery<T> q = em.createQuery(cq);

        q.setFirstResult(start);
        q.setMaxResults(range);

        return q.getResultList();
    }

    protected List<Predicate> buildConditions(Map<String, Object> filters, Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            Predicate predicate = buildCondition(entry, root, cb);
            if (predicate != null) {
                predicates.add(predicate);
            }
        }

        return predicates;
    }

    protected Predicate buildCondition(Map.Entry<String, Object> entry, Root<T> root, CriteriaBuilder cb) {
        return cb.equal(root.get(entry.getKey()), entry.getValue());
    }

    protected Order buildOrderBy(String sortField, SortOrder sortOrder, CriteriaBuilder cb, Root<T> root) {
        Order order = null;
        if (sortOrder != null && Validator.isNotNull(sortField)) {
            switch (sortOrder) {
                case ASCENDING:
                    order = cb.asc(root.get(sortField));
                    break;
                case DESCENDING:
                    order = cb.desc(root.get(sortField));
                    break;
            }
        }
        return order;
    }
}
