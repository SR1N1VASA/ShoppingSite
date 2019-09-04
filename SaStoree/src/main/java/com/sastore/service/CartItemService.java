package com.sastore.service;

import java.util.List;

import com.sastore.domain.Book;
import com.sastore.domain.CartItem;
import com.sastore.domain.Order;
import com.sastore.domain.ShoppingCart;
import com.sastore.domain.User;

public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addBookToCartItem(Book book, User user, int qty);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}
