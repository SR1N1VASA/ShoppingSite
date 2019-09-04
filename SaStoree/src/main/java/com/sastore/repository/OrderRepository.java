package com.sastore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sastore.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}