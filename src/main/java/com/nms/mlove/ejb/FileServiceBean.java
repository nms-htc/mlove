/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.File;
import com.nms.mlove.service.FileService;
import javax.ejb.Stateless;

/**
 *
 * @author NamTA
 */
@Stateless
public class FileServiceBean extends AbstractService<File> implements
        FileService {

    private static final long serialVersionUID = 4912271302978902126L;

    public FileServiceBean() {
        super(File.class);
    }

    @Override
    protected void onAfterPersist(File entity) {
        super.onAfterPersist(entity);

        Long id = entity.getId();
        if (id != null && entity.isUpload()) {
            String filename = entity.getFilePath();
        }
        em.merge(entity);

    }
}
