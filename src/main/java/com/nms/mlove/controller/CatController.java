/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.controller;

import com.nms.mlove.entity.Cat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.CatService;
import javax.ejb.EJB;

/**
 *
 * @author MinhDT
 */
public class CatController extends AbstractController<Cat> {

    private static final long serialVersionUID = 2047444173869810767L;

    @EJB
    private CatService service;

    @Override
    protected BaseService<Cat> getBaseService() {
        return service;
    }
}
