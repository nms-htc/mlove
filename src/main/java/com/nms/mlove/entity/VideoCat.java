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
@DiscriminatorValue("Video")
@XmlRootElement
public class VideoCat extends Cat {

    private static final long serialVersionUID = 6827683559210424535L;

}
