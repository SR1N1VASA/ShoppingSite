package com.sastore.service;

import com.sastore.domain.BillingAddress;
import com.sastore.domain.Order;
import com.sastore.domain.Payment;
import com.sastore.domain.ShippingAddress;
import com.sastore.domain.ShoppingCart;
import com.sastore.domain.User;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user);
	
	Order findOne(Long id);
}
