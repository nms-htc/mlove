package com.nms.mlove.web.admin;

import com.nms.mlove.entity.Video;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.VideoService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoController extends AbstractManagedBean<Video> {

    private static final long serialVersionUID = -3630143264850369479L;

    @EJB
    private VideoService service;

    @Override
    protected BaseService<Video> getBaseService() {
        return service;
    }

    @Override
    protected Video initEntity() {
        return new Video();
    }

}
