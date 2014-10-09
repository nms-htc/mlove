package com.nms.mlove.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Video")
@XmlRootElement
public class VideoCat extends Cat {

    private static final long serialVersionUID = 6827683559210424535L;

}
