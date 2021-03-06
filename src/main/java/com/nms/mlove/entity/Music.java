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
@DiscriminatorValue("Music")
@XmlRootElement
public class Music extends Product {

    private static final long serialVersionUID = -3339759100221558758L;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "THUMBNAIL_FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected File thumbFile;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "MAIN_FILEID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected File musicFile;

    public Music() {
    }

    public File getThumbFile() {
        return thumbFile;
    }

    public void setThumbFile(File thumbFile) {
        this.thumbFile = thumbFile;
    }

    public File getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(File musicFile) {
        this.musicFile = musicFile;
    }

    @Override
    public MusicCat getCat() {
        return (MusicCat) super.getCat();
    }
}
