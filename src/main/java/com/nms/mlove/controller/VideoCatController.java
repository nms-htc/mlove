/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.mlove.controller;

import com.nms.mlove.entity.VideoCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.VideoCatService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author NamTA
 */
@Named
@ViewScoped
public class VideoCatController extends AbstractController<VideoCat>
{
    private static final long serialVersionUID = -4528503654927658378L;
    
    @EJB
    private VideoCatService service;

    @Override
    protected BaseService<VideoCat> getBaseService()
    {
        return service;
    }
    
}
