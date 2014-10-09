package com.nms.mlove.entity;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nguyen Trong Cuong
 * @since 09/10/2014
 * @version 1.0
 */
@Entity
@DiscriminatorValue("Video")
@XmlRootElement
public class Video extends Product {

    private static final long serialVersionUID = 3192890565957976525L;
    
    @ManyToOne
    @JoinColumn(name = "CAT_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected VideoCat cat;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "THUMBNAIL_FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected File thumbFile;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "MAIN_FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected File videoFile;

    public Video() {
    }

    public File getThumbFile() {
        return thumbFile;
    }

    public void setThumbFile(File thumbFile) {
        this.thumbFile = thumbFile;
    }

    public File getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(File videoFile) {
        this.videoFile = videoFile;
    }

    public VideoCat getCat() {
        return cat;
    }

    public void setCat(VideoCat cat) {
        this.cat = cat;
    }
}
