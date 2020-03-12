package org.anonymous.test;

import org.anonymous.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author child
 * 2019/6/23 13:48
 * java persistence query language
 * structured query language
 */
public class JPQLDemo {

    /**
     * sql: select * from tb_customer
     * jpql: from Customer
     */
    @Test
    public void findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("from Customer");
        List list = query.getResultList();
        System.out.println(list.get(0).getClass()); //class org.anonymous.domain.Customer
        list.forEach(System.out::println);

        em.close();
    }

    /**
     * 排序
     */
    @Test
    public void findAll$() {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("from Customer order by custId desc");
        List list = query.getResultList();
        list.forEach(System.out::println);

        em.close();
    }

    /**
     * 统计
     */
    @Test
    public void count() {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("select count(*) from Customer ");
        Object result = query.getSingleResult();
        System.out.println("result = " + result);

        em.close();
    }

    /**
     * 分页
     */
    @Test
    public void page() {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("from Customer ")
                .setFirstResult(2) //limit 2, 2
                .setMaxResults(2);
        System.out.println("query.getClass() = " + query.getClass()); //class org.hibernate.jpa.internal.QueryImpl

        List list = query.getResultList();
        list.forEach(System.out::println);

        em.close();
    }

    @Test
    public void like() {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("from Customer where custName like ?")
                .setParameter(1, "ro%");
        List list = query.getResultList();
        list.forEach(System.out::println);

        em.close();
    }

}
