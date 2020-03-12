package org.anonymous.dao;

import org.anonymous.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author child
 * 2019/6/23 16:42
 */
public interface Mapper extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    /**
     * 注: 这里 from 后的 参数是 实体类中 @Entity(name = "") 那么属性值, 如果不指定 name 属性值,
     * 默认就是 Customer, 首字母大写
     *
     * @return
     */
    @Query("from customer ")
    List<Customer> findAllByJPQL();

    @Query("from customer where custId = ?1")
    Customer findOneByJPQL(long id);

    @Query("from customer where custName = ?2 and custId = ?1")
    Customer findOneByNameAndId(Long id, String name);

    /**
     * @param newName
     * @param id
     * @return Modifying queries can only use void or int/Integer as return type! //修改方法的返回值： void， int， Integer
     * TransactionRequiredException: Executing an update/delete query // update/delete： 必须使用事务
     * 且事务默认会回滚， 可以将回滚设置为 false
     * @see Query 注解默认是 查询， 联合 {@link Modifying} 表示修改
     */
    @Modifying
    @Query("update customer set custName = ?1 where custId = ?2")
    int updateByJPQL(String newName, Long id);

    //-------------------------------------
    // sql
    //-------------------------------------

    /**
     * 一条数据封装一个对象
     *
     * @return
     */
    @Query(value = "select * from tb_customer", nativeQuery = true)
    List<Customer> findAllBySQL();

    /**
     * 一条数据封装一个 Object[]
     *
     * @return
     */
    @Query(value = "select * from tb_customer", nativeQuery = true)
    List<Object[]> findAllBySql();

    @Query(value = "select * from tb_customer where cust_id = ?", nativeQuery = true)
    Customer findOneBySql(long id);

    /**
     * 注: 与 jpql 不同的是, sql 形式的多参数占位符如果不指定 占位符序号, 不会报错, 会返回 null
     * jpql 如果出现类型不一致的情况, 会报错
     *
     * @param name
     * @param id
     * @return
     */
    @Query(value = "select * from tb_customer where cust_id = ?2 and cust_name = ?1", nativeQuery = true)
    Customer findOneSQL(String name, Long id);

    //---------------------------------
    // 根据命名规范命名方法, 不需要注解
    //---------------------------------

    /**
     * select * from tb_customer where cust_id = ?
     * findByCustId = ? -- findBy 开头, 条件属性结尾(驼峰命名)(custId 小写好像也不会报错)
     *
     * @param id
     * @return No property "..." found for type Customer!
     * No property custid found for type Customer! Did you mean 'custId'?
     */
    Customer custId(Long id);

    /**
     * 模糊查询: 必须以 "Like" 结尾, L 大写
     *
     * @param name
     * @return
     */
    List<Customer> custNameLike(String name);

    /**
     * where cust_id=? and (cust_name like ? escape ?)
     *
     * @param id
     * @param name
     * @return
     */
    List<Customer> custIdAndCustNameLike(Long id, String name);

}
