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
 * 2019/6/15 15:13
 * @see BoundListOperations#remove(long, java.lang.Object)
 * <p>
 * 参数一: 移除元素个数
 * 参数二: 移除 list 中指定 value
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class Test2$1List_LeftPush {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

   /* @Autowired
    public Test2$1List_LeftPush(@Qualifier("redisTemplate") RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }*/

    @Before
    public void leftPush() {
        BoundListOperations<String, String> list = redisTemplate.boundListOps("list");
        Long count = list.leftPushAll("1", "2", "3", "3", "3");
        System.err.println("count = " + count); //count = 5
    }

    @Test
    public void remove() {
        BoundListOperations<String, String> list = redisTemplate.boundListOps("list");
        //移除
        Long remove = list.remove(3, "3");
        System.err.println("remove = " + remove); //remove = 3

        List<String> strings = list.range(0, list.size());
        System.err.println("strings = " + strings); //strings = [2, 1]

    }

    //防止与之前的 key 冲突: 这里在所有的 方法运行完毕之后, 清空指定的 key
    @After
    public void delete() {
        Boolean b = redisTemplate.delete("list");
        System.err.println("b = " + b); //
    }

}
