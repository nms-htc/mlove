package com.nms.mlove.ejb;

import com.nms.mlove.entity.BaseEntity;
import com.nms.mlove.entity.BaseEntity_;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.util.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
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

    protected final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T find(Object id) {
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
    public int count(Map<String, Object> filters) {

        return executeTemplate(
                () -> { return Long.class;},
                (cb, root) -> {return cb.count(root);},
                (cb,root) -> {return buildConditions(filters, root, cb).toArray(new Predicate[] {});},
                null,
                (q) -> {return q.getSingleResult();})
                .intValue();
    }

    @Override
    public List<T> search(int start, int range, String sortField,
            boolean asc, Map<String, Object> filters) {
//        Class<List> classess = List<T>.class;
//        return executeTemplate(
//                () -> { return List.class;},
//                (cb, root) -> {return cb.count(root);},
//                (cb,root) -> {return buildConditions(filters, root, cb).toArray(new Predicate[] {});},
//                null,
//                (q) -> {return q.getResult();});
//                
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(entityClass);
//        Root<T> root = cq.from(entityClass);
//        cq.select(root);
//
//        List<Predicate> predicates = buildConditions(filters, root, cb);
//
//        if (predicates != null && !predicates.isEmpty()) {
//            cq.where(predicates.toArray(new Predicate[]{}));
//        }
//
//        Order[] orders = buildOrder(sortField, asc, cb, root);
//
//        if (orders != null && orders.length > 0) {
//            cq.orderBy(orders);
//        }
//
//        TypedQuery<T> q = em.createQuery(cq);
//
//        q.setFirstResult(start);
//        q.setMaxResults(range);
//
//        return q.getResultList();
        return null;
    }

    protected List<Predicate> buildConditions(Map<String, Object> filters, Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        filters.entrySet().stream().map((entry) -> buildCondition(entry, root, cb))
                .filter((predicate) -> (predicate != null))
                .forEach((predicate) -> {
                    predicates.add(predicate);
                });

        return predicates;
    }

    protected Predicate buildCondition(Map.Entry<String, Object> entry, Root<T> root, CriteriaBuilder cb) {
        return cb.equal(root.get(entry.getKey()), entry.getValue());
    }

    protected Order[] buildOrder(String sortField, boolean asc, CriteriaBuilder cb, Root<T> root) {
        List<Order> orders = new ArrayList<>();

        if (sortField == null || sortField.isEmpty()) {
            orders.add(cb.desc(root.get(BaseEntity_.modifiedDate)));
        } else {
            if (asc) {
                orders.add(cb.asc(root.get(sortField)));
            } else {
                orders.add(cb.desc(root.get(sortField)));
            }
        }
        return orders.toArray(new Order[]{});
    }

    /**
     * Select template
     *
     * @param <R> type of returning
     * @param supplier supply <pre>Class\<R\></pre>
     *
     * @param processSelect process select
     * @param restricter procss where statement
     * @param orderer process order statement
     * @param querier restrict number of query and return result.
     * @return
     */
    protected <R> R executeTemplate(Supplier<Class<R>> supplier,
            BiFunction<CriteriaBuilder, Root<T>, Selection<? extends R>> processSelect,
            BiFunction<CriteriaBuilder, Root<T>, Predicate[]> restricter,
            BiFunction<CriteriaBuilder, Root<T>, Order[]> orderer,
            Function<TypedQuery<R>, R> querier) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<R> cq = cb.createQuery(supplier.get());
        Root<T> root = cq.from(entityClass);

        // restrict the selecting
        cq.select(processSelect.apply(cb, root));

        // restrict the result
        Predicate[] restrictions = null;
        if (restricter != null) {
            restrictions = restricter.apply(cb, root);
        }
        if (restrictions != null && restrictions.length > 0) {
            cq.where(restrictions);
        }
        // order the result.
        Order[] orders = null;
        if (orderer != null) {
            orders = orderer.apply(cb, root);
        }
        if (orders != null && orders.length > 0) {
            cq.orderBy(orders);
        }

        TypedQuery<R> q = em.createQuery(cq);

        return querier.apply(q);
    }
    
    protected List<T> executeTemplate(
            BiFunction<CriteriaBuilder, Root<T>, Predicate[]> restricter,
            BiFunction<CriteriaBuilder, Root<T>, Order[]> orderer,
            Function<TypedQuery<T>, List<T>> querier) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        // restrict the selecting
        cq.select(root);

        // restrict the result
        Predicate[] restrictions = null;
        if (restricter != null) {
            restrictions = restricter.apply(cb, root);
        }
        if (restrictions != null && restrictions.length > 0) {
            cq.where(restrictions);
        }
        // order the result.
        Order[] orders = null;
        if (orderer != null) {
            orders = orderer.apply(cb, root);
        }
        if (orders != null && orders.length > 0) {
            cq.orderBy(orders);
        }

        TypedQuery<T> q = em.createQuery(cq);

        return querier.apply(q);
    }
    
    protected int commonCount(Function<TypedQuery<Long>, Long> processResult) {
//        return (int)commonQuery(cb -> cb.createQuery(Long.class), 
//                null, null, null, processResult);
        return 1;
    }
    
    protected <R, S extends R> Object commonQuery(
            Function<CriteriaBuilder, CriteriaQuery<R>> supplierQuery, 
            Function<CriteriaBuilder, Selection<S>> supplierSelection,
            BiFunction<CriteriaBuilder, Selection<S>, Predicate[]> processPredicates,
            BiFunction<CriteriaBuilder, Selection<S>, Order[]> processOrder,
            Function<TypedQuery<R>, Object> processResult) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<R> cq = supplierQuery.apply(cb);
        Selection<S> root = supplierSelection.apply(cb);
        cq.select(root);
        
        // restrict the result
        Predicate[] restrictions = null;
        if (processPredicates != null) {
            restrictions = processPredicates.apply(cb, root);
        }
        if (restrictions != null && restrictions.length > 0) {
            cq.where(restrictions);
        }
        // order the result.
        Order[] orders = null;
        if (processOrder != null) {
            orders = processOrder.apply(cb, root);
        }
        if (orders != null && orders.length > 0) {
            cq.orderBy(orders);
        }

        TypedQuery<R> q = em.createQuery(cq);
        return processResult.apply(q);
    }
}
