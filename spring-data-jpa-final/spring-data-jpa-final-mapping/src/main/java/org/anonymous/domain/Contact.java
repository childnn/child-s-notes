package org.anonymous.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author child
 * 2019/6/24 14:51
 */
@Data
@Entity
@Table(name = "tb_contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private Long conId; //主键

    @Column(name = "con_name")
    private String conName; //姓名

    @Column(name = "con_gender")
    private String conGender; //性别

    @Column(name = "con_mobile")
    private String conMobile; //手机

    @Column(name = "con_email")
    private String conEmail; //邮箱

    @Column(name = "con_position")
    private String conPosition; //职位

    @Column(name = "con_memo")
    private String conMemo; //备注

    @ManyToOne(targetEntity = Customer.class)
    /**
     * name: 从表的外键名 (多的一方)
     * referencedColumnName: 外键来源(外键对应的主键: 主表的主键)
     */
    @JoinColumn(name = "con_cust_id", referencedColumnName = "cust_id")
    private Customer customer;

}
