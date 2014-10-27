/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.service;

import com.nms.mlove.entity.User;
import java.util.List;

/**
 *
 * @author MinhDT
 */
public interface UserService extends BaseService<User> {

    public User findByUP(String username, String password);

    public User findByUsername(String username);

    public User updateUserPassword(User user, String oldPassword, String newPassword);

    public User updateUserPassword(User user);

    public List<User> findAdministrators();

}
