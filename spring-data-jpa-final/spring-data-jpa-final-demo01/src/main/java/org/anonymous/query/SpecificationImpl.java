package org.anonymous.query;

import org.anonymous.domain.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * @author child
 * 2019/6/24 10:41
 */
public class SpecificationImpl implements Specification<Customer> {

    /**
     * @param root            A root type in the from clause. Query roots always reference entities.
     * @param query           顶层查询对象, 用来自定义查询
     * @param criteriaBuilder 条件构造对象
     * @return
     */
    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Path<Object> custName = root.get("custId");
//        Expression<Class<? extends Customer>> type = root.type();
        return criteriaBuilder.equal(custName, 9);
    }
}
