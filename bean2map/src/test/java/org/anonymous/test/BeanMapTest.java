package org.anonymous.test;

import org.anonymous.domain.User;
import org.anonymous.util.Bean0Map;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author child
 * 2019/6/24 12:45
 */
public class BeanMapTest {

    @Test
    public void test() {
        User user = new User();
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("id", 1);
            put("name", "rose");
        }};
        Bean0Map.map2Bean(map, user); //有返回值但是不需要接收: 本身就是往原对象上封装
        System.out.println("user = " + user);
    }

    @Test
    public void test1() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        User user = Bean0Map.map2Bean$(new HashMap<String, Object>() {{
            put("id", 1);
            put("name", "rose");
        }}, User.class); //必须要有 set 方法: 否则属性就是 默认值
        System.out.println("user = " + user);
    }

    @Test
    public void test2() {
        User user = new User();
        user.setId(2);
        user.setName("jack");
        Map<String, Object> map = Bean0Map.bean2Map(user); //必须要有 get 方法
        System.out.println("map = " + map);
    }
}
