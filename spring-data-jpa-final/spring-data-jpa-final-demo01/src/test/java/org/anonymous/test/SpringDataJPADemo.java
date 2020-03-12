package org.anonymous.test;

import org.anonymous.dao.Mapper;
import org.anonymous.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author child
 * 2019/6/23 16:53
 */
@ContextConfiguration(locations = "classpath:appContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJPADemo {

    @Autowired
    private Mapper mapper;

    private String date;

    @Before
    public void getDate() throws FileNotFoundException {
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " ";
        PrintStream out = new PrintStream(new FileOutputStream("logs/test.log", true));
        System.setOut(out);
    }

    /**
     * 代理对象
     *
     * @see org.springframework.aop.framework.JdkDynamicAopProxy
     * @see SimpleJpaRepository
     * @see SimpleJpaRepository#findById(java.lang.Object)
     */
    @Test
    public void findOne() {

        Optional<Customer> opt = mapper.findById(3L);
        Customer customer = opt.get();
        System.err.println("customer = " + customer);
        System.err.println(1234242);
    }

    @Transactional
    @Test
    public void getOne() {
        Customer customer = mapper.getOne(9L);
        System.out.println(date + customer);
    }

    @Test
    public void findAll() throws IOException {
        List<Customer> list = mapper.findAll();
        File f = new File("logs/test.log");
//        f.mkdirs(); //文件夹
        f.createNewFile(); //文件: 必须要有上级目录
        PrintStream file = new PrintStream(f);
        System.setErr(file);
        list.forEach(System.err::println);
    }

    @Test
    public void testFileOut() throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream("logs/test.log", true));
        List<Customer> list = mapper.findAll();

        list.forEach(System.err::println);

        for (Customer customer : list) {
            System.setOut(out);
            System.out.println(date + customer);
        }
    }

    /**
     * update:
     * create: 不指定主键 id, 一定是 insert
     */
    @Test
    public void save() {
        Customer customer = new Customer()
//                .setCustId(1L) //
                .setCustName("杰克")
                .setCustSource("network")
                .setCustAddress("湖北省武汉市")
                .setCustLevel("VIP")
                .setCustIndustry("teacher")
                .setCustPhone("13163249276");
        Customer save = mapper.save(customer);
        System.out.println(date + save);
    }

    //update: 先查询, 后修改.
    @Test
    public void save$() {
        /*Customer customer = new Customer()
                .setCustId(1L) //
                .setCustName("杰克")
                .setCustSource("network")
                .setCustAddress("湖北省武汉市")
                .setCustLevel("VIP")
                .setCustIndustry("teacher")
                .setCustPhone("13163249276");*/
        Customer customer = mapper.findById(10L).get();
        customer.setCustName("JAVA");
        Customer save = mapper.save(customer);
        System.out.println(date + save);
    }

    // delete: id 不存在就报错
    @Test
    public void delete() {
        mapper.deleteById(10L);
    }

    //统计
    @Test
    public void count() {
        long count = mapper.count();
        System.out.println(date + "count:" + count);
    }

    @Test
    public void exist() {
        boolean b = mapper.existsById(9L); //存在返回 true, 否则返回 false
        System.out.println(date + "exist:" + b);
    }

    @Test
    public void test() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(date);
    }

}
