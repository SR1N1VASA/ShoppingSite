package com.sastore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sastore.domain.CartItem;
import com.sastore.domain.Electronic;
import com.sastore.domain.ElectronicCartItem;
import com.sastore.domain.ElectronicToCartItem;
import com.sastore.domain.Order;
import com.sastore.domain.ShoppingCart;
import com.sastore.domain.User;
import com.sastore.repository.ElectronicCartItemRepository;
import com.sastore.repository.ElectronicToCartItemRepository;
import com.sastore.service.ElectronicCartItemService;

@Service
public class ElectronicCartItemServiceImpl implements ElectronicCartItemService{
	
	@Autowired
	private ElectronicCartItemRepository electroniccartItemRepository;
	
	@Autowired
	private ElectronicToCartItemRepository electronicToCartItemRepository;
	
	public List<ElectronicCartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return electroniccartItemRepository.findByShoppingCart(shoppingCart);
	}
	
	public ElectronicCartItem updateECartItem(ElectronicCartItem electroniccartItem) {
		BigDecimal bigDecimal = new BigDecimal(electroniccartItem.getElectronic().getOurPrice()).multiply(new BigDecimal(electroniccartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		electroniccartItem.setSubtotal(bigDecimal);
		
		electroniccartItemRepository.save(electroniccartItem);
		
		return electroniccartItem;
	}
	
	public ElectronicCartItem addElectronicToCartItem(Electronic electronic, User user, int qty) {
		List<ElectronicCartItem> electroniccartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (ElectronicCartItem electroniccartItem : electroniccartItemList) {
			if(electronic.getId() == electroniccartItem.getElectronic().getId()) {
				electroniccartItem.setQty(electroniccartItem.getQty()+qty);
				electroniccartItem.setSubtotal(new BigDecimal(electronic.getOurPrice()).multiply(new BigDecimal(qty)));
				electroniccartItemRepository.save(electroniccartItem);
				return electroniccartItem;
			}
		}
		
		ElectronicCartItem electroniccartItem = new ElectronicCartItem();
		electroniccartItem.setShoppingCart(user.getShoppingCart());
		electroniccartItem.setElectronic(electronic);
		
		electroniccartItem.setQty(qty);
		electroniccartItem.setSubtotal(new BigDecimal(electronic.getOurPrice()).multiply(new BigDecimal(qty)));
		electroniccartItem = electroniccartItemRepository.save(electroniccartItem);
		
		ElectronicToCartItem electronicToCartItem = new ElectronicToCartItem();
		electronicToCartItem.setElectronic(electronic);
		electronicToCartItem.setElectroniccartItem(electroniccartItem);
		electronicToCartItemRepository.save(electronicToCartItem);
		
		return electroniccartItem;
	}
	
	public ElectronicCartItem findById(Long id) {
		return electroniccartItemRepository.findById(id).orElse(null);
	}
	
	
	public ElectronicCartItem save(ElectronicCartItem electroniccartItem) {
		return electroniccartItemRepository.save(electroniccartItem);
	}

	public List<ElectronicCartItem> findByOrder(Order order) {
		return electroniccartItemRepository.findByOrder(order);
	}


	@Override
	public void removeECartItem(ElectronicCartItem electroniccartItem) {
		electronicToCartItemRepository.deleteByElectroniccartItem(electroniccartItem);
		electroniccartItemRepository.delete(electroniccartItem);
	}

}
