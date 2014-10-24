/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.service;

import com.nms.mlove.entity.Cat;
import com.nms.mlove.entity.Product;
import java.util.List;

/**
 *
 * @author MinhDT
 * @param <T>
 */
public interface CatService<T extends Cat> extends BaseService<T> {

    public T findByTitle(String title);
}
