/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.mlove.ejb;

import com.nms.mlove.entity.Video;
import javax.ejb.Stateless;

/**
 *
 * @author NamTA
 */
@Stateless
public class VideoServiceBean extends ProductServiceBean<Video>
{
    private static final long serialVersionUID = -2224958569242409588L;

    public VideoServiceBean()
    {
        super(Video.class);
    }
    
}
