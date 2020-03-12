package org.anonymous.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author child
 * 2019/6/23 16:42
 * 一对多:
 * 本类中的 contacts 属性如果设置为 Set<Contact> 集合, 在执行级联保存时, 会报 StackOverFlowError
 */
@Entity(name = "customer")
@Table(name = "tb_customer")
@Data
public class Customer {

    /**
     * @see Id 声明主键
     * @see GeneratedValue 配置主键生成策略
     * @see Column 指定表的字段
     */
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId; //主键

    @Column(name = "cust_name")
    private String custName; //名称

    @Column(name = "cust_source")
    private String custSource; //客户来源

    @Column(name = "cust_level")
    private String custLevel; //级别

    @Column(name = "cust_industry")
    private String custIndustry; //行业

    @Column(name = "cust_phone")
    private String custPhone; // 联系方式

    @Column(name = "cust_address")
    private String custAddress; // 客户地址

    /*@OneToMany(targetEntity = Contact.class)
     */
    /**
     * name: 外键字段名(从表)
     * referencedColumnName: 与外键相对的字段名(主表)
     *//*
    @JoinColumn(name = "con_cust_id", referencedColumnName = "cust_id")*/
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();

}
