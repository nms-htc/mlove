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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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

    @Inject
    private MusicController musicController;

    @Inject
    private VideoController videoController;

    @Override
    protected BaseService<File> getBaseService()
    {
        return service;
    }

    public void uploadOnchange()
    {
        current.setUpload(!current.isUpload());
    }

    public void prepareEntity(File entity, String type)
    {
        if (entity == null)
            entity = new File();
        super.prepareEntity(entity);
        File.FileType fileType = File.FileType.UNKNOW;
        switch (type)
        {
            case "THUMB_IMAGE":
                fileType = File.FileType.THUMB_IMAGE;
                break;
            case "THUMB_MUSIC":
                fileType = File.FileType.THUMB_MUSIC;
                break;
            case "THUMB_VIDEO":
                fileType = File.FileType.THUMB_VIDEO;
                break;
            case "IMAGE":
                fileType = File.FileType.IMAGE;
                break;
            case "MUSIC":
                fileType = File.FileType.MUSIC;
                break;
            case "VIDEO":
                fileType = File.FileType.VIDEO;
                break;
        }

        getCurrent().setFileType(fileType);
    }
    
    @Override
    public void resetEntity()
    {
        super.resetEntity();
    }

    public void resetEntity(String type)
    {
        super.resetEntity();
        File.FileType fileType = File.FileType.UNKNOW;

        switch (type)
        {
            case "THUMB_IMAGE":
                fileType = File.FileType.THUMB_IMAGE;
                break;
            case "THUMB_MUSIC":
                fileType = File.FileType.THUMB_MUSIC;
                break;
            case "THUMB_VIDEO":
                fileType = File.FileType.THUMB_VIDEO;
                break;
            case "IMAGE":
                fileType = File.FileType.IMAGE;
                break;
            case "MUSIC":
                fileType = File.FileType.MUSIC;
                break;
            case "VIDEO":
                fileType = File.FileType.VIDEO;
                break;
        }

        getCurrent().setFileType(fileType);
    }

    public String getFileType()
    {
        String allType = "*";
        if (getCurrent().getFileType() == File.FileType.IMAGE
                || getCurrent().getFileType() == File.FileType.THUMB_IMAGE
                || getCurrent().getFileType() == File.FileType.THUMB_MUSIC
                || getCurrent().getFileType() == File.FileType.THUMB_VIDEO)
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
        if (getCurrent().getFileType() == File.FileType.IMAGE
                || getCurrent().getFileType() == File.FileType.THUMB_IMAGE
                || getCurrent().getFileType() == File.FileType.THUMB_MUSIC
                || getCurrent().getFileType() == File.FileType.THUMB_VIDEO)
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

    public void handleReplaceFile(FileUploadEvent event)
    {
        try
        {
            current.setIs(event.getFile().getInputstream());
            current.setFileSize(event.getFile().getSize());
            current.setContentType(event.getFile().getContentType());
            //current.setFilePath(event.getFile().getFileName());
            current.setUpload(true);

            String filePath = service.getFileStoreLocation(current);

            filePath = filePath + java.io.File.separator + current.getId().
                    toString();
            service.validateDirectories(filePath);
            filePath = filePath + java.io.File.separator + event.getFile().
                    getFileName();

            service.storeFile(current.getIs(), filePath);
            current.setFilePath(filePath);
        }
        catch (IOException ex)
        {
            MessageUtil.addGlobalErrorMessage(ex);
        }
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

    @Override
    protected void onAfterPersist()
    {
        try
        {
            updateToUpperController();
        }
        catch (CloneNotSupportedException e)
        {
            MessageUtil.addGlobalErrorMessage(e);
        }
        finally
        {
            super.onAfterPersist();
        }
    }

    @Override
    protected void onAfterUpdate()
    {
        try
        {
            updateToUpperController();
        }
        catch (CloneNotSupportedException e)
        {
            MessageUtil.addGlobalErrorMessage(e);
        }
        finally
        {
            super.onAfterPersist();
        }
    }
    

    private void updateToUpperController() throws CloneNotSupportedException
    {
        if (getCurrent().getFileType() == File.FileType.MUSIC)
        {
            musicController.getCurrent().
                    setMusicFile((File) current.clone());
        }
        else if (getCurrent().getFileType() == File.FileType.THUMB_MUSIC)
        {

            musicController.getCurrent().
                    setThumbFile((File) current.clone());
        }
        else if (getCurrent().getFileType() == File.FileType.VIDEO)
        {

            videoController.getCurrent().
                    setVideoFile((File) current.clone());
        }
        else if (getCurrent().getFileType() == File.FileType.THUMB_VIDEO)
        {

            videoController.getCurrent().
                    setThumbFile((File) current.clone());
        }
    }

}
