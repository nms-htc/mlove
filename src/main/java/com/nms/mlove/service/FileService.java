/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.mlove.service;

import com.nms.mlove.entity.File;
import java.io.InputStream;

/**
 *
 * @author NamTA
 */
public interface FileService extends BaseService<File>
{
    public String getFileStoreLocation(File file);
    
    public void validateDirectories(String path);
    
    public void storeFile(InputStream isStream, String filePath);
}
