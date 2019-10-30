package com.example.test;

import com.alibaba.fastjson.JSON;
import com.example.bo.MyBo;
import com.example.vo.MyVo;
import org.junit.Test;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MiaoOne
 * 2019/8/1 21:44
 */
public class BeanCopierTest {

    @Test
    public void test() {
        MyVo vo = new MyVo().setId(1).setName("jack");
        MyBo bo = new MyBo();
        BeanCopier beanCopier = BeanCopier.create(vo.getClass(), bo.getClass(), true);
        beanCopier.copy(vo, bo, new Converter() {
            @Override
            public Object convert(Object o, Class aClass, Object o1) {
                System.out.println("o = " + o);
                System.out.println("aClass = " + aClass);
                System.out.println("o1 = " + o1);
                return null;
            }
        });
        System.out.println(bo);

    }

    /**
     * 不能直接封装集合
     */
    @Test
    public void test1() {
        ArrayList<MyVo> sourceList = new ArrayList<>();
        ArrayList<MyBo> destList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyVo vo = new MyVo().setId(1).setName("jack");
            sourceList.add(vo);
            MyBo myBo = new MyBo();
            destList.add(myBo);
        }
        BeanCopier beanCopier = BeanCopier.create(sourceList.getClass(), destList.getClass(), false);
        beanCopier.copy(sourceList, destList, null);
        System.out.println(destList);
    }

    /**
     * 反射调用 方法, 获取属性值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void test2() throws InvocationTargetException, IllegalAccessException {
        MyVo vo = new MyVo().setId(1).setName("jack");
        Class klass = vo.getClass();
        Field[] declaredFields = klass.getDeclaredFields();
        Method[] declaredMethods = klass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
//            System.out.println(declaredMethod);
            String methodName = declaredMethod.getName();
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                String firstLetter = name.charAt(0) + "";
                String methodName0 = "get" + name.replaceFirst(firstLetter, firstLetter.toUpperCase());
//                System.out.println("methodName = " + methodName);
                if (methodName.equals(methodName0)) {
                    Object attrValue = declaredMethod.invoke(vo);
                    System.out.println(name + " = " + attrValue);
                }
            }
        }
    }

    @Test
    public void test3() {
        List<String> strings = JSON.parseArray("[123, ,,,, 123]", String.class); // [123, 123]
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void test4() {
//        List<Object> list = Collections.emptyList();
//        ArrayList<String> list = new ArrayList<>();
        List<String> list = Collections.singletonList("");
        ArrayList<String> strings = new ArrayList<String>() {{
            add("123");
        }};
        for (Object o : list) {
            System.out.println("".equals(o));
        }
    }

    @Test
    public void test5() {
        int[] sourceArr = {1, 2, 3};
        int[] destArr = new int[4];
        /**
         * 参数一: 原数组
         * 参数二: 待复制数据的起始索引
         * 参数三: 目标数组
         * 参数四: 复制到目标数组的起始索引
         * 参数五: 复制数据的长度
         */
        System.arraycopy(sourceArr, 1, destArr, 1, 2);
        for (int i : destArr) {
            System.out.println(i);
        }
    }

}
