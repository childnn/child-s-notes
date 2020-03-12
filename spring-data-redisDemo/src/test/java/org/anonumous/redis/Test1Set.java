package org.anonumous.redis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * @author child
 * 2019/6/15 14:11
 * key - String
 * value - Set<String>  -- 无序, 不重复
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class Test1Set {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    //设置数据
    @Before
    public void add() {
        BoundSetOperations<String, String> set = redisTemplate.boundSetOps("set");
        System.err.println("set.getClass() = " + set.getClass()); //class org.springframework.data.redis.core.DefaultBoundSetOperations

        //这个返回值是什么意思?
        Long count = set.add("jack", "rose", "tom", "tom"); //放入重复元素: 自动去重
        System.err.println("count = " + count); //count = 0
    }

    //获取所有数据
    @Test
    public void get() {
        Set<String> set = redisTemplate.boundSetOps("set").members();
        System.err.println("set = " + set); //set = [tom, rose, jack]
    }

    //移除指定 value
    @Test
    public void remove() {
        //返回移除 set 中 value 的个数
        Long remove = redisTemplate.boundSetOps("set").remove("tom", "rose");
        System.err.println("remove = " + remove); //remove = 2
        Set<String> set = redisTemplate.boundSetOps("set").members();
        System.err.println("set = " + set); //set = [jack]
    }

    //清空指定 key 对应的 set
    @Test
    public void delete() {
        Boolean b = redisTemplate.delete("set");
        System.err.println("b = " + b); //b = true
        Set<String> set = redisTemplate.boundSetOps("set").members();
        System.err.println("set = " + set); //set = []
    }


}
