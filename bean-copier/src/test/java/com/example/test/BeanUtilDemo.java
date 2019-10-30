package com.example.test;

import com.example.bo.MyBo;
import com.example.util.BeanUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MiaoOne
 * 2019/8/8 9:38
 */
public class BeanUtilDemo {
    @Test
    public void test() throws Exception {
        Map<String, Object> beanMap = new HashMap<String, Object>()/*{{
            put("id", 1);
            put("name", "jack");
        }}*/;
        beanMap.put("id", 1);
        beanMap.put("name", "jack");

        MyBo vo = BeanUtil.map2Bean(beanMap, MyBo.class);
        System.out.println("vo = " + vo);
    }
}
