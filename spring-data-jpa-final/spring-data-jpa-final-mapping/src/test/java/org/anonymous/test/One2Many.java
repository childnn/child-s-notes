package org.anonymous.test;

import org.anonymous.dao.ContactMapper;
import org.anonymous.dao.CustomerMapper;
import org.anonymous.domain.Contact;
import org.anonymous.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author child
 * 2019/6/24 15:32
 */
@ContextConfiguration(locations = "classpath:appContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class One2Many {

    @Autowired
    @Qualifier("customerMapper")
    private CustomerMapper customerMapper;

    @Autowired
    @Qualifier("contactMapper")
    private ContactMapper contactMapper;

    private String date;

    @Before
    public void init() throws FileNotFoundException {
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.setOut(new PrintStream(new FileOutputStream("logs/one2many.log", true)));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void add() {
        Customer customer = new Customer();
//        customer.setCustId(1L);
        customer.setCustName("肉丝2");
//        customer.setCustSource("");
//        customer.setCustLevel("");
//        customer.setCustIndustry("");
        customer.setCustPhone("1234");
//        customer.setCustAddress("");
//        customer.setContacts(new HashSet<Contact>());
        Contact contact = new Contact();
//        contact.setConId(0L);
        contact.setConName("jack1");
//        contact.setConGender("");
//        contact.setConMobile("");
        contact.setConEmail("1234@qq.com");
//        contact.setConPosition("");
//        contact.setConMemo("");
//        contact.setCustomer(new Customer());

//        customer.getContacts().add(contact);

        contact.setCustomer(customer);

        customerMapper.save(customer);
        contactMapper.save(contact);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void cascadeAdd() {
        Customer customer = new Customer();
        customer.setCustName("google");

        Contact contact = new Contact();
        contact.setConName("百度");
        contact.setCustomer(customer);

        customer.getContacts().add(contact);

        Customer save = customerMapper.save(customer);
        //注： 这里不能输出, 原因是 调用 Customer 的 toString() 时, 会调用 Contact 的 toString() 方法
        // 而 Contact 中有 Customer 属性, 又会 调用 Customer 的 toString() 方法, 形成 "递归"..
//        System.out.println(date + "save = " + save);
    }

}
