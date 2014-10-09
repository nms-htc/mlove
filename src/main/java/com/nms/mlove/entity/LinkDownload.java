package com.nms.mlove.entity;

import java.util.Date;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Nguyen Trong Cuong
 * @since 09/10/2014
 * @version 1.0
 */
@Entity
@Table(name = "ML_LINK")
public class LinkDownload extends BaseEntity {

    private static final long serialVersionUID = -5673234761406985644L;
    
    @ManyToOne
    @JoinColumn(name = "FILE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected File file;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date expireDate;

    public LinkDownload() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
}
