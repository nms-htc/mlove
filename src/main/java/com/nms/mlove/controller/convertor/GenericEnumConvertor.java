/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.controller.convertor;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author MinhDT
 */
@FacesConverter("enumConvertor")
public class GenericEnumConvertor implements Converter {

    public static final String ATTRIBUTE_ENUM_TYPE = "GenericEnumConvertor.enumType";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Class<Enum> enumType = (Class<Enum>) component.getAttributes().get(ATTRIBUTE_ENUM_TYPE);

        try {
            return Enum.valueOf(enumType, value);
        } catch (IllegalArgumentException e) {
            throw new ConverterException(new FacesMessage("Value is not enum of type" + enumType), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Enum) {
            component.getAttributes().put(ATTRIBUTE_ENUM_TYPE, value.getClass());
            return ((Enum<?>) value).name();
        } else {
            throw new ConverterException(new FacesMessage("Value is not of enum" + value));
        }
    }

}
