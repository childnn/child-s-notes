package com.example.test;

import com.example.bo.MyBo;
import com.example.vo.MyVo;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.ConversionService;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2019/12/14 15:06
 */
public class SpringBeanUtilsTest {
    @Test
    public void test() {
        MyVo source = new MyVo().setId(1).setName("jack").setDate("2019-12-12");
        MyBo target = new MyBo();
        BeanUtils.copyProperties(source, target);
        System.out.println("target = " + target);
    }

    public static void main(String[] args) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(new MyBo());
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println("propertyDescriptor = " + propertyDescriptor);
        }
        System.out.println();

        PropertyDescriptor id = beanWrapper.getPropertyDescriptor("id");
        Method readMethod = id.getReadMethod();
        System.out.println("readMethod = " + readMethod);

        System.out.println();

        ConfigurablePropertyAccessor c = PropertyAccessorFactory.forDirectFieldAccess(new MyBo());
        ConversionService conversionService = c.getConversionService();
    }
}
