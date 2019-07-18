package com.joseph.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface StateRepository extends CrudRepository<State, Long> {
}
