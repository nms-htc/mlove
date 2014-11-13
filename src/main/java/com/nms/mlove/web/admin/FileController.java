/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.web.admin;

import com.nms.mlove.entity.File;
import com.nms.mlove.service.BaseService;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author NamTA
 */
@Named
@ViewScoped
public class FileController extends AbstractManagedBean<File> {

    private static final long serialVersionUID = 5719979201870314764L;

    @Override
    protected BaseService<File> getBaseService() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected File initEntity() {
        return new File();
    }

}
