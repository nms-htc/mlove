/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mlove.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MinhDT
 */
public class Validator {

    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    public static final String DEFAULT_DATETIME_PATTERN = "dd/MM/yyyy - hh:mm:ss";
    public static final String DEFAULT_TIMEPATTERN = "hh:mm:ss";

    public static boolean isNull(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotNull(String value) {
        return !isNull(value);
    }

    public static String dateToStr(Date date, String pattern) {
        Format f = new SimpleDateFormat(pattern);
        return f.format(date);
    }

    public static String dateToStr(Date date) {
        Format f = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return f.format(date);
    }
}
