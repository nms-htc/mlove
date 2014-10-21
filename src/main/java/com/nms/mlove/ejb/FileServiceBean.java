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
        if (id != null)
        {
            String filename = entity.getFilePath();
            String directory = getFileStoreLocation(entity)
                    + java.io.File.pathSeparator + id.toString()
                    + java.io.File.pathSeparator;

            entity.setFilePath(directory + filename);

            java.io.File file = new java.io.File(directory);
            if (!file.exists())
            {
                file.mkdirs();
            }

            file = new java.io.File(entity.getFilePath());

            OutputStream outputStream = null;

            try
            {
                file.createNewFile();

                outputStream
                = new FileOutputStream(file);

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = entity.getIs().read(bytes)) != -1)
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
            em.merge(entity);
        }
    }

    public String getFileStoreLocation(File entity)
    {
        String unknow = "/admin/upload/other";
        if (entity.getFileType() == File.FileType.IMAGE)
        {
            return AppConfig.props.getProperty("imageFileLcation", unknow);
        }
        else if (entity.getFileType() == File.FileType.MUSIC)
        {
            return AppConfig.props.getProperty("musicFileLcation", unknow);
        }
        else if (entity.getFileType() == File.FileType.VIDEO)
        {
            return AppConfig.props.getProperty("videoFileLcation", unknow);
        }
        else
        {
            return unknow;
        }
    }
}
