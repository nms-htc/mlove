/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.mlove.ejb;

import com.nms.mlove.entity.MusicCat;
import com.nms.mlove.service.MusicCatService;
import javax.ejb.Stateless;

/**
 *
 * @author NamTA
 */
@Stateless
public class MusicCatServiceBean extends CatServiceBean<MusicCat> implements MusicCatService
{
    private static final long serialVersionUID = 3184346321046321253L;

    public MusicCatServiceBean()
    {
        super(MusicCat.class);
    }
    
}
