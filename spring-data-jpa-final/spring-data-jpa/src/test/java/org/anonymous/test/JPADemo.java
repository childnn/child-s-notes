package org.anonymous.test;

import org.anonymous.domain.Customer;
import org.anonymous.util.JPAUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author child
 * 2019/6/22 15:56
 * @see EntityManager#persist(Object)  - C
 * @see EntityManager#remove(Object)  - D
 * @see EntityManager#merge(Object) - U
 * @see EntityManager#find(Class, Object) - R
 * @see EntityManager#getReference(Class, Object) - R
 */
public class JPADemo {

    private EntityManagerFactory emf;

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory("jpa");
    }

    @After
    public void destroy() {
        emf.close();
    }

    /**
     * jpa-save
     * 1. 加载配置文件创建工厂: 实体管理器工厂
     * 2. 通过实体管理器工厂获取实体管理器
     * 3. 获取事务对象, 开启事务
     * 4. 完成 crud 操作
     * 5. 提交/回滚事务
     * 6. 释放资源
     */
    @Test
    public void testSave() {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
//        System.out.println(entityManagerFactory.getClass()); //class org.hibernate.internal.SessionFactoryImpl
        EntityManager em = emf.createEntityManager();
        System.out.println("entityManager = " + em.getClass());
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Customer customer = new Customer()
//                .setCustId(1L) //如果注解指定了 自动生成主键(mysql), 就不能再指定 主键了, 否则 detached entity passed to persist异常
                .setCustName("杰克")
                .setCustSource("network")
                .setCustAddress("湖北省武汉市")
                .setCustLevel("VIP")
                .setCustIndustry("teacher")
                .setCustPhone("13163249276");
        //执行保存操作
        em.persist(customer);
        //提交事务
        transaction.commit();
        //释放资源
        em.close();
    }

    /**
     * @see EntityManager#find(java.lang.Class, java.lang.Object)
     * 立即加载: 无论是否使用结果对象, sql 都会立即执行
     */
    @Test
    public void testFind() {
        EntityManager em = JPAUtil.getEntityManager();
        Customer customer = em.find(Customer.class, 1L);
        System.out.println("customer.getClass() = " + customer.getClass());
        // 无论是否执行
//        System.out.println(customer);
    }

    /**
     * @see EntityManager#getReference(Class, Object)
     * lazy fetched
     * 懒加载: 不适用的到的结果对象, 就不会执行实际的查询操作
     * getClass 不算使用对象
     * toString() 就算对对象进行操作
     */
    @Test
    public void testReference() {
        EntityManager em = JPAUtil.getEntityManager();
        Customer reference = em.getReference(Customer.class, 1L);
        System.out.println(reference.getClass()); //class org.anonymous.domain.Customer$HibernateProxy$bAVB0bhN
        // 如果下面的注释解开, 就会执行 sql, 否则不会执行
//        System.out.println("reference = " + reference);
    }

    /**
     * remove: 先查询再删除
     * 如果指定 主键的在数据库中不存在,
     */
    @Test
    public void testRemove() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = em.find(Customer.class, 2L);
        em.remove(customer);

        tx.commit();
        em.close();
    }

    //先查询再修改
    @Test
    public void testUpdate() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = em.find(Customer.class, 4L);
        customer.setCustAddress("harbin");

        Customer merge = em.merge(customer);
        System.out.println("merge = " + merge);

        tx.commit();

        em.close();
    }

}
