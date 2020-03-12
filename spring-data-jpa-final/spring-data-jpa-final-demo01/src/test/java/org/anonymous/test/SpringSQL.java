package org.anonymous.test;

import org.anonymous.dao.Mapper;
import org.anonymous.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author child
 * 2019/6/24 9:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext.xml")
public class SpringSQL {

    @Autowired
    @Qualifier("mapper")
    private Mapper mapper;

    private String date;

    @Before
    public void init() throws FileNotFoundException {
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " ";
        System.setOut(new PrintStream(new FileOutputStream("logs/sql.log", true)));
    }

    @Test
    public void findAll() {
        List<Customer> list = mapper.findAllBySQL();
        list.forEach(c -> System.out.println(date + c));
    }

    @Test
    public void findAll$() {
        List<Object[]> list = mapper.findAllBySql();
        list.forEach(o -> System.out.println(date + Arrays.toString(o)));
    }

    @Test
    public void findOne() {
        Customer customer = mapper.findOneBySql(9L);
        System.out.println(date + "customer = " + customer);
    }

    @Test
    public void findOne$() {
        Customer customer = mapper.findOneSQL("jack", 9L);
        System.out.println(date + "customer = " + customer);
    }

    @Test
    public void name() {
        Customer customer = mapper.custId(9L);
        System.out.println(date + "customer = " + customer);
    }

    @Test
    public void namelike() {
        List<Customer> list = mapper.custNameLike("ja%");
        list.forEach(c -> System.out.println(date + c));
    }

    @Test
    public void multi() {
        List<Customer> list = mapper.custIdAndCustNameLike(9L, "ja%");
        list.forEach(c -> System.out.println(date + c));
    }

}
