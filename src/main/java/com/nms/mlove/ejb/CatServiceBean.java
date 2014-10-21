/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.ejb;

import com.nms.mlove.entity.Cat;
import com.nms.mlove.service.CatService;
import javax.ejb.Stateless;

/**
 *
 * @author MinhDT
 */
@Stateless
public class CatServiceBean extends AbstractService<Cat> implements CatService {

    private static final long serialVersionUID = 3906692967558682211L;

    public CatServiceBean() {
        super(Cat.class);
    }

}
