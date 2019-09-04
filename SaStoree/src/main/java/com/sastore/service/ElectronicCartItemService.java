package com.sastore.service;

import java.util.List;

import com.sastore.domain.Book;
import com.sastore.domain.CartItem;
import com.sastore.domain.Electronic;
import com.sastore.domain.ElectronicCartItem;
import com.sastore.domain.Order;
import com.sastore.domain.ShoppingCart;
import com.sastore.domain.User;

public interface ElectronicCartItemService {
	List<ElectronicCartItem> findByShoppingCart(ShoppingCart shoppingCart);

	
	ElectronicCartItem addElectronicToCartItem(Electronic electronic, User user, int qty);
	
	ElectronicCartItem findById(Long id);
	
	
	ElectronicCartItem save(ElectronicCartItem electroniccartItem);
	
	List<ElectronicCartItem> findByOrder(Order order);

	ElectronicCartItem updateECartItem(ElectronicCartItem electroniccartItem);

	void removeECartItem(ElectronicCartItem findById);
}
