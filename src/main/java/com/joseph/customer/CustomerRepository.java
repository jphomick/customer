package com.joseph.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface CustomerRepository extends CrudRepository<Customer, Long>{
}
