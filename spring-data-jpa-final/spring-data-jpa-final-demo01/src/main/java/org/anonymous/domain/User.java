package org.anonymous.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author child
 * 2019/6/24 9:22
 */
//@Data
@Getter
@Setter
public class User {
    private int id;
    private String name;
    private Customer customer;

    public static void main(String[] args) {
        User user = new User();
        user.setId(0);
        user.setName("");
        user.setCustomer(new Customer());
    }
}
