/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.controller;

import com.nms.mlove.entity.BaseEntity;
import com.nms.mlove.model.AbstractLazyDataModel;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.util.JsfUtil;
import com.nms.mlove.util.MessageUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author MinhDT
 * @param <T>
 */
public abstract class AbstractController<T extends BaseEntity> implements Serializable {

    private static final long serialVersionUID = -1374442757454130534L;

    protected T current;
    private T itemSearch = initEntity();
    protected LazyDataModel<T> model;
    protected SelectItem[] selectItems;

    public AbstractController() {
    }

    public void resetEntity() {
        current = null;
    }

    public void searchItem() {
        resetModel();
    }

    private void resetModel() {
        model = null;
    }

    public void prepareEntity(T entity) {
        current = entity;
    }

    /**
     * Call back method persist action
     */
    protected void onBeforePersist() {
    }

    /**
     * Call back method persist action
     */
    protected void onAfterPersist() {
        current = initEntity();
    }

    /**
     * Call back method persist action
     */
    protected void onPersistSuccess() {
        MessageUtil.addGlobalInfoMessage("success");
    }

    /**
     * Call back method persist action
     *
     * @param t
     */
    protected void onPersistError(Throwable t) {
        MessageUtil.addGlobalErrorMessage("error", t);
    }

    /**
     * Persist entity to db
     */
    public void persist() {
        onBeforePersist();
        try {
            getBaseService().persist(current);
            onPersistSuccess();
        } catch (Exception e) {
            onPersistError(e);
        }
        onAfterPersist();
    }

    /**
     * Call back method update action
     */
    protected void onBeforeUpdate() {
    }

    /**
     * Call back method update action
     */
    protected void onAfterUpdate() {
        current = initEntity();
    }

    /**
     * Call back method update action
     */
    protected void onSuccessUpdate() {
        MessageUtil.addGlobalInfoMessage("success");
    }

    /**
     * Call back method update action
     *
     * @param t
     */
    protected void onErrorUpdate(Throwable t) {
        MessageUtil.addGlobalErrorMessage("error", t);
    }

    /**
     * Update entity and save to db
     */
    public void update() {
        onBeforeUpdate();
        try {
            getBaseService().update(current);
            onSuccessUpdate();
        } catch (Exception e) {
            onErrorUpdate(e);
        }
        onAfterUpdate();
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onBeforeRemove(T entity) {
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onAfterRemove(T entity) {
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onSuccessRemove(T entity) {
        MessageUtil.addGlobalInfoMessage("success");
    }

    /**
     * Call back method remove action
     *
     * @param entity
     * @param t
     */
    protected void onErrorRemove(T entity, Throwable t) {
        MessageUtil.addGlobalErrorMessage("error", t);
    }

    /**
     * Remove entity from db
     *
     * @param entity entity instance for removing
     */
    public void remove(T entity) {
        onBeforeRemove(entity);
        try {
            getBaseService().remove(entity);
            onSuccessRemove(entity);
        } catch (Exception e) {
            onErrorRemove(entity, e);
        }
        onAfterRemove(entity);
    }

    /**
     * Initilize a new instance of the entity;
     *
     * @return new instanse of entity
     */
    protected T initEntity() {
        try {
            return getEntityClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, "Can not instantilize entity.", ex);
        }
        return null;
    }

    /**
     * Factory method for initilize a new instance of LazyDataModel (Primefaces)
     *
     * @return new instance of LazyDataModel for the entity.
     */
    protected LazyDataModel<T> initDataModel() {
        return new AbstractLazyDataModel<T>(getItemSearch()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected BaseService<T> getService() {
                return getBaseService();
            }
        };
    }

    /**
     * Factoty method for BasicService EJB
     *
     * @return BasicSerivce instanse
     */
    protected abstract BaseService<T> getBaseService();

    /* getters and setters */
    public T getCurrent() {

        if (current == null) {
            current = initEntity();
        }

        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public LazyDataModel<T> getModel() {
        if (model == null) {
            model = initDataModel();
        }
        return model;
    }

    public void setModel(LazyDataModel<T> model) {
        this.model = model;
    }

    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public SelectItem[] getSelectItems() {
        if (selectItems == null) {
            selectItems = JsfUtil.getSelectItems(getBaseService().findAll(), false);
        }
        return selectItems;
    }

    public void setSelectItems(SelectItem[] selectItems) {
        this.selectItems = selectItems;
    }

    public T getItemSearch() {
        if (itemSearch == null) {
            itemSearch = initEntity();
        }
        
        return itemSearch;
    }

    public void setItemSearch(T itemSearch) {
        this.itemSearch = itemSearch;
    }
}
