/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.controller;

import com.nms.mlove.entity.File;
import com.nms.mlove.service.BaseService;
import com.nms.mlove.service.FileService;
import com.nms.mlove.util.AppConfig;
import com.nms.mlove.util.MessageUtil;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author NamTA
 */
@Named
@ViewScoped
public class FileController extends AbstractController<File>
{
    private static final long serialVersionUID = 5719979201870314764L;

    @EJB
    private FileService service;

    @Override
    protected BaseService<File> getBaseService()
    {
        return service;
    }

    public void resetEntityToImage()
    {
        resetEntity();
        getCurrent().setFileType(File.FileType.IMAGE);
    }

    public void resetEntityToMusic()
    {
        resetEntity();
        getCurrent().setFileType(File.FileType.MUSIC);
    }

    public void resetEntityToVideo()
    {
        resetEntity();
        getCurrent().setFileType(File.FileType.VIDEO);
    }

    public String getFileType()
    {
        String allType = "*";
        if (getCurrent().getFileType() == File.FileType.IMAGE)
        {
            return AppConfig.props.getProperty("imageFileType", allType);
        }
        else if (getCurrent().getFileType() == File.FileType.MUSIC)
        {
            return AppConfig.props.getProperty("musicFileType", allType);
        }
        else if (getCurrent().getFileType() == File.FileType.VIDEO)
        {
            return AppConfig.props.getProperty("videoFileType", allType);
        }
        else
        {
            return allType;
        }
    }

    public long getFileLimit()
    {
        String unlimit = "0";
        String limitSize = unlimit;
        if (getCurrent().getFileType() == File.FileType.IMAGE)
        {
            limitSize = AppConfig.props.getProperty("imageFileSizeLimit",
                    unlimit);
        }
        else if (getCurrent().getFileType() == File.FileType.MUSIC)
        {
            limitSize = AppConfig.props.getProperty("musicFileSizeLimit",
                    unlimit);
        }
        else if (getCurrent().getFileType() == File.FileType.VIDEO)
        {
            limitSize = AppConfig.props.getProperty("videoFileSizeLimit",
                    unlimit);
        }

        return Long.parseLong(limitSize);
    }

    public void handleFileUpload(FileUploadEvent event)
    {
        try
        {
            current.setIs(event.getFile().getInputstream());
            current.setFileSize(event.getFile().getSize());
            current.setContentType(event.getFile().getContentType());
            current.setFilePath(event.getFile().getFileName());
            current.setUpload(true);
        }
        catch (IOException ex)
        {
            MessageUtil.addGlobalErrorMessage(ex);
        }
    }

}
