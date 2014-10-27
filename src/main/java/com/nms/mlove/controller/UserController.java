/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.controller;

import com.nms.mlove.entity.User;
import com.nms.mlove.entity.User.Group;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.UserService;
import com.nms.mlove.util.MessageUtil;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MinhDT
 */
@Named
@ViewScoped
public class UserController extends AbstractController<User>{
    private static final long serialVersionUID = 4560879301727409137L;
    
    private SelectItem[] groupSelectItems;
    
    @EJB
    private UserService service;

    @Override
    protected BaseService<User> getBaseService() {
        return service;
    }

    @Override
    protected User initEntity() {
        return new User();
    }
    
    public void prepareChangePassword(User user) {
        current = user;
    }
    
    public void changePassword() {
        try {
            service.updateUserPassword(current);
            MessageUtil.addGlobalInfoMessage("change-password-successfull");
        } catch (Exception e) {
            MessageUtil.addGlobalErrorMessage("change-password-error", e);
        }
    }

    public SelectItem[] getGroupSelectItems() {
        if (groupSelectItems == null) {
            groupSelectItems = new SelectItem[Group.values().length];
            for (int i = 0; i < Group.values().length; i++) {
                groupSelectItems[i] = new SelectItem(Group.values()[i],
                        MessageUtil.getBundleMessage(Group.values()[i].name()));
            }
        }
        return groupSelectItems;
    }

    public void setGroupSelectItems(SelectItem[] groupSelectItems) {
        this.groupSelectItems = groupSelectItems;
    }
}
