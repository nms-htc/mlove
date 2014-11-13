/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.model;

import com.nms.mlove.entity.BaseEntity;
import com.nms.mlove.service.BaseService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author MinhDT
 * @param <T>
 */
public abstract class AbstractLazyDataModel<T extends BaseEntity> extends LazyDataModel<T> {

    private static final long serialVersionUID = -1137464869996262401L;

    protected abstract BaseService<T> getService();

    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public T getRowData(String rowKey) {
        return getService().find(parserRowKey(rowKey));
    }

    @Override
    public Object getRowKey(T object) {
        return ((BaseEntity) object).getId();
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        // modifing filter cirterias.
        modifyModelFilters(filters);

        boolean asc = false;
        if (sortOrder != null && sortOrder == SortOrder.ASCENDING) {
            asc = true;
        }
        this.setRowCount(getService().count(filters));
        return getService().search(first, pageSize, sortField, asc, filters);
    }

    /**
     * Override this method to add default predicate for query data model.
     *
     * @param filters
     */
    protected void modifyModelFilters(Map<String, Object> filters) {
        /* nothing to say. */
    }

}
