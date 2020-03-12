package org.anonymous.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author child
 * 2019/6/24 16:51
 */
@Data
@Table(name = "tb_role")
@Entity(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

}
