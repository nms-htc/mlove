/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.mlove.controller;

import com.nms.mlove.entity.Video;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.VideoService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author NamTA
 */
@Named
@ViewScoped
public class VideoController extends AbstractController<Video>
{
    private static final long serialVersionUID = -3630143264850369479L;
    
    @EJB
    private VideoService service;
    
    @Override
    protected BaseService<Video> getBaseService()
    {
        return service;
    }
    
}
