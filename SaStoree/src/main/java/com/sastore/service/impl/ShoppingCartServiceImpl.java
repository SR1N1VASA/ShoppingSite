package com.sastore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sastore.domain.CartItem;
import com.sastore.domain.ElectronicCartItem;
import com.sastore.domain.ShoppingCart;
import com.sastore.repository.ShoppingCartRepository;
import com.sastore.service.CartItemService;
import com.sastore.service.ElectronicCartItemService;
import com.sastore.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private ElectronicCartItemService electroniccartItemService;
	
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		BigDecimal cartTotal = new BigDecimal(0);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : cartItemList) {
			if(cartItem.getBook().getInStockNumber() > 0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal = cartTotal.add(cartItem.getSubtotal());
			}
		}
		
		shoppingCart.setGrandTotal(cartTotal);
		
		shoppingCartRepository.save(shoppingCart);
		
List<ElectronicCartItem> electroniccartItemList = electroniccartItemService.findByShoppingCart(shoppingCart);
		
		for (ElectronicCartItem electroniccartItem : electroniccartItemList) {
			if(electroniccartItem.getElectronic().getInStockNumber() > 0) {
				electroniccartItemService.updateECartItem(electroniccartItem);
				cartTotal = cartTotal.add(electroniccartItem.getSubtotal());
			}
		}
		
		shoppingCart.setGrandTotal(cartTotal);
		
		shoppingCartRepository.save(shoppingCart);
		
		return shoppingCart;
	}
	
	public void clearShoppingCart(ShoppingCart shoppingCart) {
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		List<ElectronicCartItem> electroniccartItemList = electroniccartItemService.findByShoppingCart(shoppingCart);
		
		for (ElectronicCartItem electroniccartItem : electroniccartItemList) {
			electroniccartItem.setShoppingCart(null);
			electroniccartItemService.save(electroniccartItem);
		}

		for (CartItem cartItem : cartItemList) {
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		}
		shoppingCart.setGrandTotal(new BigDecimal(0));
		
		shoppingCartRepository.save(shoppingCart);
	}

}
