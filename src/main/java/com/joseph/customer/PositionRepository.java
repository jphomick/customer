package com.joseph.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PositionRepository extends CrudRepository<Position, Long> {
}
