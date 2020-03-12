package org.anonumous.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author child
 * 2019/6/15 11:56
 * key - String
 * value - string
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class Test0Value {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 非 public class, 包外访问不到
     * /@see org.springframework.data.redis.core.DefaultBoundValueOperations
     * 往 map 中存入 k-v
     */
    @Before
    public void setValue() {
        BoundValueOperations<String, String> operations = redisTemplate.boundValueOps("name");
        //operations = class org.springframework.data.redis.core.DefaultBoundValueOperations
        System.err.println("operations = " + operations.getClass());

        operations.set("anonymous");
    }

    //从集合(map)中获取 指定 key 的 value
    @Test
    public void getValue() {
        String name = redisTemplate.boundValueOps("name").get();
        System.err.println("name = " + name); //name = anonymous
    }

    @After
    public void deleteValue() {
        Boolean b = redisTemplate.delete("name");
        System.err.println("b = " + b); //b = true
        String name = redisTemplate.boundValueOps("name").get();
        System.err.println("name = " + name); //name = null
    }

    @Test
    public void test() {
        // org.springframework.data.redis.core.TimeoutUtils
        Boolean name = redisTemplate.expire("name", 1000, TimeUnit.MINUTES);
    }

}
