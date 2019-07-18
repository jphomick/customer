package com.joseph.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
