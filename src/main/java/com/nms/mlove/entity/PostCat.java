/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cuong
 */
@Entity
@DiscriminatorValue("Post")
@XmlRootElement
public class PostCat extends Cat {

    private static final long serialVersionUID = 3306967170692838123L;

}
