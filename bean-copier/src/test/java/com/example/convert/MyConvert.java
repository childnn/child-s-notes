package com.example.convert;

import net.sf.cglib.core.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2019/12/14 13:35
 */
public class MyConvert implements Converter {

    /**
     * @param sourceValue     原值.
     * @param targetParamType 目标值的数据类型.
     * @param setterName      方法名 setter.
     * @return
     */
    @Override
    public Object convert(Object sourceValue, Class targetParamType, Object setterName) {
        if (LocalDate.class.isAssignableFrom(targetParamType)) {
            return LocalDate.parse(String.valueOf(sourceValue), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return sourceValue;
    }
}
