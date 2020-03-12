package org.anonumous.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author child
 * 2019/6/15 14:30
 * key - String
 * value - List<String>  -- 有序, 可以重复
 * 左压栈: 先进栈的排在右边
 * 右压栈: 先进栈的排在左边
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class Test2$0List_LeftPush {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 左压栈
     * leftPush(value)
     * leftPushAll(动态数组)
     * 先进栈的排在右边
     */
    @Before
    public void leftPush() {
        BoundListOperations<String, String> list = redisTemplate.boundListOps("list");
        System.err.println("list.getClass() = " + list.getClass()); //class org.springframework.data.redis.core.DefaultBoundListOperations
        //左压栈: 先进栈的排在 "右边" -- "倒序"
        Long count = list.leftPushAll("1", "2", "3");
        System.err.println("count = " + count); //count = 3
    }

    /**
     * range(startIndex, endIndex): 获取指定索引范围的 数据
     * index(index): 获取指定索引的单个元素
     */
    @Test
    public void get() {
        BoundListOperations<String, String> list = redisTemplate.boundListOps("list");
        List<String> strings = list.range(0, list.size());
        System.err.println("strings = " + strings); //strings = [3, 2, 1]

        String value = list.index(0);
        System.err.println("value = " + value); //value = 3
    }

    /**
     * 删除
     * leftPop()
     * rightPop()
     * delete(): RedisTemplate 的方法, 所有 value 类型共有(删除指定的 key, 也就删除了该 key 对应的 value)
     */
    @After
    public void pop() {
        BoundListOperations<String, String> list = redisTemplate.boundListOps("list");
        //返回弹出的元素
        String str = list.leftPop();
        System.err.println("str = " + str); //str = 3
        List<String> strings = list.range(0, list.size());
        System.err.println("strings = " + strings); //strings = [2, 1]

        //如果不写 delete() 删除指定 key 的 value, 则重复执行 上述 @Test, 会在 "list" 中插入重复的元素
        Boolean b = redisTemplate.delete("list");
        System.err.println("b = " + b); //b = true
    }

}
