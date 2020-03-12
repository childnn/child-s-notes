package org.anonymous.test;

import org.anonymous.dao.Mapper;
import org.anonymous.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author child
 * 2019/6/23 22:57
 */
@ContextConfiguration(locations = "classpath:appContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJPQL {

    @Autowired
    private Mapper mapper;

    private String date;

    @Before
    public void init() throws FileNotFoundException {
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " ";
        PrintStream out = new PrintStream(new FileOutputStream("logs/jpql.log", true));
        System.setOut(out);
    }

    @Test
    public void findAll() {
        List<Customer> list = mapper.findAllByJPQL();
        list.forEach(c -> System.out.println(date + c));
    }

    @Test
    public void findOneById() {
        Customer customer = mapper.findOneByJPQL(9L);
        System.out.println("customer = " + customer);
    }

    @Test
    public void findOneByNameAndId() {
        Customer customer = mapper.findOneByNameAndId(8L, "杰克");
        System.out.println(date + "customer = " + customer);
    }

    /**
     * @see Transactional
     * @see org.springframework.test.annotation.Rollback
     */
    @Transactional
    @Rollback(false) //设置不自动回滚: 如果不设置 false, 这里的修改不会成功
    @Test
    public void update() {
        int num = mapper.updateByJPQL("jack", 9L);
        System.out.println(date + "num = " + num);
    }
}
