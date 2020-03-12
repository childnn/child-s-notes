package org.anonymous.test;

import org.anonymous.domain.Customer;
import org.anonymous.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * @author child
 * 2019/6/22 19:59
 * 核心对象: Session {@link Session}
 * 数据库连接的桥梁对象. 非线程安全
 */
public class HibernateDemo {

    //save
    @Test
    public void test() {
        //加载核心配置: 默认名称 hibernate.cfg.xml
        Configuration configuration = new Configuration().configure(/*"hibernate.cfg.xml"*/);
        //创建 sessionFactory 对象: datasource
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //获取 session 对象: connection -- 链接对象, 非线程安全
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction tx = session.beginTransaction();
        //业务
        Customer customer = new Customer()
                .setCustName("杰克")
                .setCustSource("network")
                .setCustAddress("湖北省武汉市")
                .setCustLevel("VIP")
                .setCustIndustry("teacher")
                .setCustPhone("13163249276");
        Object save = session.save(customer);
        //提交事务
        tx.commit();
        //释放资源
        session.close();
        sessionFactory.close();
    }


    /**
     * @see Session#get
     * @see Session#load
     */
    @Test
    public void find() {
        Session session = HibernateUtil.openSession();
//        Customer customer = session.get(Customer.class, 3L);
//        System.out.println("customer = " + customer.getClass()); //原pojo对象: 没有则返回 null

        Customer customer1 = session.load(Customer.class, 0L);
        System.out.println("customer1.getClass() = " + customer1.getClass()); //代理对象: 在没有上述缓存的前提下- 没有数据也有代理对象
//        System.out.println("customer1 = " + customer1); // 如果没有对象, 就会报错: ObjectNotFoundException - 上面回去 Class 类型不会报错, 这里调用 toString() 会报错
        session.close();
    }

    /**
     * update: 先查询再修改
     */
    @Test
    public void update() {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        tx.begin();
        Customer customer = session.get(Customer.class, 3L);
        System.out.println("customer = " + customer);
        customer.setCustName("jack");
        session.update(customer);

        tx.commit();
        Customer customer1 = session.get(Customer.class, 3L);
        System.out.println("customer1 = " + customer1);

        session.close();
    }

    /**
     * delete: 先查询再删除
     * 该删除方式, 可以实现级联删除(多表关联的映射)
     */
    @Test
    public void delete() {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        transaction.begin();

        Customer customer = session.get(Customer.class, 4L);

        session.delete(customer);

        transaction.commit();

        session.close();
    }

    @Test
    public void saveOrUpdate() {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        tx.begin();

        Customer customer = new Customer()
//                .setCustId(6L) //如果指定 id, 则数据库中必须存在该数据, 否则会报错 // 如果不指定 id, 就是 create
                .setCustName("rose")
                .setCustSource("network")
                .setCustAddress("湖北省武汉市")
                .setCustLevel("VIP")
                .setCustIndustry("teacher")
                .setCustPhone("13163249276");
        session.saveOrUpdate(customer);

        tx.commit();

        session.close();
    }

    /**
     * HQL: Hibernate Query Language
     * 指定对象
     */
    @Test
    public void findAll() {
        Session session = HibernateUtil.openSession();
        Query<Customer> query = session.createQuery("from Customer", Customer.class);
        List<Customer> list = query.list();
        list.forEach(System.out::println);

        session.close();
    }

    /**
     * sql
     * 指定 sql 语句
     */
    @Test
    public void findAll$() {
        Session session = HibernateUtil.openSession();
        NativeQuery<Customer> sqlQuery = session.createNativeQuery("select * from tb_customer", Customer.class);
        List<Customer> list = sqlQuery.list();//一条数据封装成 Object 数组中的一个元素
        list.forEach(System.out::println);
    }

}
