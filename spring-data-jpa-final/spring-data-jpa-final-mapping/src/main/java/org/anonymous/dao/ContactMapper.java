package org.anonymous.dao;

import org.anonymous.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author child
 * 2019/6/24 15:13
 */
public interface ContactMapper extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {
}
