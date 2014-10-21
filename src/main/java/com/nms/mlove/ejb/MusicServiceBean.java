/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.Music;
import com.nms.mlove.service.MusicService;
import javax.ejb.Stateless;

/**
 *
 * @author NamTA
 */
@Stateless
public class MusicServiceBean extends ProductServiceBean<Music> implements
        MusicService
{
    private static final long serialVersionUID = 6740811475191004981L;

    public MusicServiceBean()
    {
        super(Music.class);
    }
}
