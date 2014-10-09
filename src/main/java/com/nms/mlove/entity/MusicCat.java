package com.nms.mlove.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nguyen Trong Cuong
 * @since 09/10/2014
 * @version 1.0
 */
@Entity
@DiscriminatorValue("Music")
@XmlRootElement
public class MusicCat extends Cat {

    private static final long serialVersionUID = 7409099370428090110L;

}
