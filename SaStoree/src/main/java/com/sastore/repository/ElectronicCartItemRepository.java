package com.sastore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sastore.domain.ElectronicCartItem;
import com.sastore.domain.Order;
import com.sastore.domain.ShoppingCart;

@Transactional
public interface ElectronicCartItemRepository extends CrudRepository<ElectronicCartItem, Long>{
	List<ElectronicCartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	List<ElectronicCartItem> findByOrder(Order order);
}
