/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.web.admin;

import com.nms.mlove.entity.Music;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.MusicService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author NamTA
 */
@Named
@ViewScoped
public class MusicController extends AbstractManagedBean<Music> {

    private static final long serialVersionUID = -7014295320270299177L;

    @EJB
    private MusicService service;

    @Override
    protected BaseService<Music> getBaseService() {
        return service;
    }

    @Override
    protected Music initEntity() {
        return new Music();
    }
}
