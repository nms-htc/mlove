package com.nms.mlove.entity;

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
@DiscriminatorValue("Post")
@XmlRootElement
public class Post extends Product {

    private static final long serialVersionUID = -505727239557431794L;
    
    @ManyToOne
    @JoinColumn(name = "CAT_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected PostCat cat;

    public Post() {
    }

    public PostCat getCat() {
        return cat;
    }

    public void setCat(PostCat cat) {
        this.cat = cat;
    }
    
}
