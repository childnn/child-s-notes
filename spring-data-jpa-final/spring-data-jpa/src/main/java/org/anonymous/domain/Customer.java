package org.anonymous.domain;

import javax.persistence.*;

/**
 * @author child
 * 2019/6/22 15:45
 * 配置映射关系:
 * 1. 实体类 - 表
 * 2. 实体类的属性 - 表中的字段
 */
@Entity(name = "customer")
@Table(name = "tb_customer")
public class Customer {

    /**
     * @see Id 声明主键
     * @see GeneratedValue 配置主键生成策略
     * @see Column 指定表的字段
     */
    @Id //主键
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }

    public Long getCustId() {
        return custId;
    }

    public Customer setCustId(Long custId) {
        this.custId = custId;
        return this;
    }

    public String getCustName() {
        return custName;
    }

    public Customer setCustName(String custName) {
        this.custName = custName;
        return this;
    }

    public String getCustSource() {
        return custSource;
    }

    public Customer setCustSource(String custSource) {
        this.custSource = custSource;
        return this;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public Customer setCustLevel(String custLevel) {
        this.custLevel = custLevel;
        return this;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public Customer setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
        return this;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public Customer setCustPhone(String custPhone) {
        this.custPhone = custPhone;
        return this;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public Customer setCustAddress(String custAddress) {
        this.custAddress = custAddress;
        return this;
    }
}
