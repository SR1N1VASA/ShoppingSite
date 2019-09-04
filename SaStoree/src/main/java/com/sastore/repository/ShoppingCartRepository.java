package com.sastore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sastore.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
