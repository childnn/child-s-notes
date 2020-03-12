package org.anonumous.redis;

import domain.Student;
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

/**
 * @author child
 * 2019/6/15 15:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class Test3$1Hash {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    private BoundHashOperations<String, String, Student> hash;

    @Before
    public void put() {
        hash = redisTemplate.boundHashOps("hashStu");
        hash.putAll(new HashMap<String, Student>() {
            private static final long serialVersionUID = 6000610468246354137L;

            {
                //这里放入 value 的对象, 必须是 实现序列化接口的类的对象
                put("a", new Student("jack", 28));
                put("b", new Student("rose", 18));
                put("c", new Student("tom", 8));
            }
        });
        List<Student> values = hash.values();
        //values = [Student{name='rose', age=18}, Student{name='tom', age=8}, Student{name='jack', age=28}]
        System.err.println("values = " + values);
    }

    @Test
    public void getValueByKey() {
        Student stu = hash.get("a");
        //stu = Student{name='jack', age=28}
        System.err.println("stu = " + stu);
    }

    @After
    public void delete() {
        Boolean aBoolean = redisTemplate.delete("hashStu");
        System.err.println("aBoolean = " + aBoolean);
    }

}
