package com.nms.mlove.entity;

import com.nms.mlove.util.JsfUtil;
import java.io.InputStream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Nguyen Trong Cuong
 * @since 09/10/2014
 * @version 1.0
 */
@Entity
@Table(name = "ML_FILE")
@XmlRootElement
public class File extends BaseEntity {
    
    public enum FileType {
        IMAGE,
        MUSIC,
        VIDEO
    }

    private static final long serialVersionUID = -2874867851464343753L;

    @Column(name = "TITLE", length = 100, nullable = false)
    protected String title;

    @Column(name = "CONTENTTYPE", length = 50)
    protected String contentType;

    @Column(name = "FILESIZE")
    protected long fileSize;

    @Column(name = "FILEPATH", length = 250)
    protected String filePath;

    @Column(name = "UPLOAD")
    protected boolean upload;

    @Transient
    @XmlTransient
    protected InputStream is;
    
    @Transient
    @XmlTransient
    protected FileType fileType = FileType.IMAGE;

    public FileType getFileType()
    {
        return fileType;
    }

    public void setFileType(FileType fileType)
    {
        this.fileType = fileType;
    }
    
    public File() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public boolean isUpload() {
        return upload;
    }

    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    public String getURL() {
        if (isUpload()) {
            return JsfUtil.getServletContextPath() + "/download-file?id=" + id;
        }
        return title;
    }

    @Override
    public String toString() {
        return "File{" + "title=" + title + ", contentType=" + contentType
                + ", fileSize=" + fileSize + ", filePath=" + filePath + '}';
    }

}
