package org.anonumous.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author child
 * 2019/6/15 15:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class Test3$0Hash {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    private BoundHashOperations<String, String, String> hash;

    /**
     * @see BoundHashOperations#putAll(java.util.Map)
     * /@see DefaultBoundHashOperations#putAll(java.util.Map)
     */
    @Before
    public void push() {
        hash = redisTemplate.boundHashOps("hash");
        Map<String, String> map = new HashMap<String, String>() {
            private static final long serialVersionUID = -3256165825202223868L;

            {
                put("a", "jack");
                put("b", "rose");
                put("c", "tom");
                put("d", "cat");
            }
        };
        hash.putAll(map);
    }

    @Test
    public void getKeysAndValue() {
        Set<String> keys = hash.keys();
        System.err.println("keys = " + keys); //keys = [a, b, c, d]
        List<String> values = hash.values();
        System.err.println("values = " + values); //values = [jack, rose, tom, cat]
        Long deleteA = hash.delete("a");
        System.err.println("deleteA = " + deleteA); //deleteA = 1
        List<String> valuesAfterDelete = hash.values();
        System.err.println("valuesAfterDelete = " + valuesAfterDelete); //valuesAfterDelete = [rose, tom, cat]
    }


    @After
    public void delete() {
        Boolean b = redisTemplate.delete("hash");
        System.err.println("b = " + b);
    }
}
