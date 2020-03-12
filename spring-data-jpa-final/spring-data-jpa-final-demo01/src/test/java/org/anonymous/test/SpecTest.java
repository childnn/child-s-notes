package org.anonymous.test;

import org.anonymous.dao.Mapper;
import org.anonymous.domain.Customer;
import org.anonymous.query.SpecificationImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author child
 * 2019/6/24 10:48
 * @see Specification
 * 自定义条件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext.xml")
public class SpecTest {

    @Autowired
    @Qualifier("mapper")
    private Mapper mapper;

    private String date;

    @Before
    public void init() throws FileNotFoundException {
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " ";
        System.setOut(new PrintStream(new FileOutputStream("logs/spec.log", true)));
    }

    @Test
    public void test() {
        Optional<Customer> one = mapper.findOne(new SpecificationImpl());
        Customer customer = one.get();
        System.out.println(date + "customer = " + customer);
    }

    @Test
    public void test$() {
        //where cust_name=? and (cust_address like ?)
        //2019-06-24 11:22:05 Customer{custId=3, custName='jack', custSource='network', custLevel='VIP', custIndustry='teacher', custPhone='13163249276', custAddress='harbin'}
        List<Customer> list = mapper.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAddress");
//                criteriaBuilder.like(custAddress, "")
                Predicate p1 = criteriaBuilder.equal(custName, "jack");
                Predicate p2 = criteriaBuilder.like(custAddress, "ha%");
                //结合以上两个条件: 且/或
//                Predicate or = criteriaBuilder.or(p1, p2); //根据业务需求
                return criteriaBuilder.and(p1, p2);
            }
        });

        list.forEach(c -> System.out.println(date + c));
    }

    /**
     * where cust_address like ? order by cust_id desc
     */
    @Test
    public void test$1() {
        List<Customer> list = mapper.findAll((Specification<Customer>) (root, query, criteriaBuilder) -> {
            // Path 返回时, 默认时 Object 泛型, 可以手动改为 期望的类型, 也可以使用 as(Class) 方法转换
            Path<Object> custAddress = root.get("custAddress");

            Expression<String> as = custAddress.as(String.class);

            return criteriaBuilder.like(as, "%武汉%");
            //排序
        }, Sort.by(Sort.Direction.DESC, "custId"));

        list.forEach(c -> System.out.println(date + c));
    }

    /**
     * SELECT *
     * FROM tb_customer
     * WHERE cust_address LIKE ".."
     * ORDER BY cust_id DESC LIMIT 0, 2;
     */
    @Test
    public void page() {
        Page<Customer> page = mapper.findAll((root, query, cb) -> {
            Expression<String> custAddress = root.get("custAddress").as(String.class);

            return cb.like(custAddress, "%武汉%");
            /**
             * 注: 第一个参数表示需要显示第几页: zero-based
             *  第二个参数: 每页显示数据条数
             */
        }, PageRequest.of(1, 2, Sort.Direction.DESC, "custId"));

        long totalElements = page.getTotalElements();
        System.out.println("totalElements = " + totalElements);

        int totalPages = page.getTotalPages();
        System.out.println("totalPages = " + totalPages);

        List<Customer> list = page.getContent();
        list.forEach(System.out::println);
    }

}
