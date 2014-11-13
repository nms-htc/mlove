/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.web.admin;

import com.nms.mlove.entity.MusicCat;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.MusicCatService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author NamTA
 */
@Named
@ViewScoped
public class MusicCatController extends AbstractManagedBean<MusicCat> {

    private static final long serialVersionUID = 8676842171949443893L;

    @EJB
    private MusicCatService service;

    @Override
    protected BaseService<MusicCat> getBaseService() {
        return service;
    }

    @Override
    protected MusicCat initEntity() {
        return new MusicCat();
    }

}
