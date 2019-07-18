package com.joseph.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CityRepository extends CrudRepository<City, Long> {
}
