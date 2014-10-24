/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.File;
import com.nms.mlove.service.FileService;
import com.nms.mlove.util.AppConfig;
import com.nms.mlove.util.MessageUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.Stateless;

/**
 *
 * @author NamTA
 */
@Stateless
public class FileServiceBean extends AbstractService<File> implements
        FileService
{
    private static final long serialVersionUID = 4912271302978902126L;
    
    public FileServiceBean()
    {
        super(File.class);
    }
    
    @Override
    protected void onAfterPersist(File entity)
    {
        super.onAfterPersist(entity);
        
        Long id = entity.getId();
        if (id != null && entity.isUpload())
        {
            String filename = entity.getFilePath();
            String directory = getFileStoreLocation(entity);
            
            validateDirectories(directory + java.io.File.separator + id.
                    toString());
            
            entity.setFilePath(directory + java.io.File.separator + id.
                    toString() + java.io.File.separator + filename);
            storeFile(entity.getIs(), entity.getFilePath());
        }
        
        em.merge(entity);
        
    }
    
    @Override
    public void validateDirectories(String path)
    {
        java.io.File file = new java.io.File(path);
        if (!file.exists())
        {
            file.mkdirs();
        }
    }
    
    @Override
    public void storeFile(InputStream iStream, String filePath)
    {
        java.io.File file = new java.io.File(filePath);
        
        OutputStream outputStream = null;
        
        try
        {
            if (file.exists())
            {
                file.delete();
            }
            
            file.createNewFile();
            
            outputStream
            = new FileOutputStream(file);
            
            int read = 0;
            byte[] bytes = new byte[1024];
            
            while ((read = iStream.read(bytes)) != -1)
            {
                outputStream.write(bytes, 0, read);
            }
            
        }
        catch (IOException e)
        {
            MessageUtil.addGlobalErrorMessage(e);
        }
        finally
        {
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException ex)
                {
                }
            }
        }
        
    }
    
    @Override
    public String getFileStoreLocation(File entity)
    {
        String dir = "/admin/upload/other";
        if (entity.getFileType() == File.FileType.IMAGE
                || entity.getFileType() == File.FileType.THUMB_IMAGE
                || entity.getFileType() == File.FileType.THUMB_MUSIC
                || entity.getFileType() == File.FileType.THUMB_VIDEO)
        {
            dir = AppConfig.props.getProperty("imageFileLocation", dir);
        }
        else if (entity.getFileType() == File.FileType.MUSIC)
        {
            dir = AppConfig.props.getProperty("musicFileLocation", dir);
        }
        else if (entity.getFileType() == File.FileType.VIDEO)
        {
            dir = AppConfig.props.getProperty("videoFileLocation", dir);
        }
        validateDirectories(dir);
        
        return dir;
    }
}
