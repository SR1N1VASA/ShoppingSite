package com.sastore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sastore.domain.CartItem;
import com.sastore.domain.ElectronicCartItem;
import com.sastore.domain.ElectronicToCartItem;

@Transactional
public interface ElectronicToCartItemRepository extends CrudRepository<ElectronicToCartItem, Long> {

	void deleteByElectroniccartItem(ElectronicCartItem electroniccartItem);
}
