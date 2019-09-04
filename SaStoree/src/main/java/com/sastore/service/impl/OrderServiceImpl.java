package com.sastore.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sastore.domain.BillingAddress;
import com.sastore.domain.Book;
import com.sastore.domain.CartItem;
import com.sastore.domain.Electronic;
import com.sastore.domain.ElectronicCartItem;
import com.sastore.domain.Order;
import com.sastore.domain.Payment;
import com.sastore.domain.ShippingAddress;
import com.sastore.domain.ShoppingCart;
import com.sastore.domain.User;
import com.sastore.repository.OrderRepository;
import com.sastore.service.CartItemService;
import com.sastore.service.ElectronicCartItemService;
import com.sastore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ElectronicCartItemService electroniccartItemService;
	
	public synchronized Order createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user) {
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		List<ElectronicCartItem> electroniccartItemList = electroniccartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			cartItem.setOrder(order);
			book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
		}
		
		for(ElectronicCartItem electroniccartItem : electroniccartItemList) {
			Electronic electronic = electroniccartItem.getElectronic();
			electroniccartItem.setOrder(order);
			electronic.setInStockNumber(electronic.getInStockNumber() - electroniccartItem.getQty());
		}
		
		order.setCartItemList(cartItemList);
		order.setElectroniccartItemList(electroniccartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}
	
	public Order findOne(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

}
