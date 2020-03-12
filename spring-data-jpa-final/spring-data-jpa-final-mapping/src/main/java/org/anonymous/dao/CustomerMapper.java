package org.anonymous.dao;

import org.anonymous.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author child
 * 2019/6/24 15:01
 */
public interface CustomerMapper extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
