/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.mlove.ejb;

import com.nms.mlove.entity.VideoCat;
import com.nms.mlove.service.VideoCatService;
import javax.ejb.Stateless;

/**
 *
 * @author NamTA
 */
@Stateless
public class VideoCatServiceBean extends CatServiceBean<VideoCat> implements VideoCatService
{
    private static final long serialVersionUID = -7600735615966059013L;

    public VideoCatServiceBean()
    {
        super(VideoCat.class);
    }
    
}
