package com.nms.mlove.service;

import com.nms.mlove.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 * @author Nguyen Trong Cuong
 * @param <T>
 * @since 09/10/2014
 * @version 1.0
 */

public interface BaseService<T extends BaseEntity> extends Serializable {

    public T find(long id);

    public List<T> findAll();

    public int countAll();

    public T persist(T entity);

    public T update(T entity);

    public void remove(T entity);

    public List<T> searchForPFDatatable(T criteria, int start, int range, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public int countForPFDatatable(Map<String, Object> filters);
}