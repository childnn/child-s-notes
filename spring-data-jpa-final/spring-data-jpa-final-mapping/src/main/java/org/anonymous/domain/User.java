package org.anonymous.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author child
 * 2019/6/24 16:51
 */
@Data
@Table(name = "tb_user")
@Entity(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    private String userName;

//    @ManyToMany
//    private List<Role> roles = new ArrayList<>();

}
