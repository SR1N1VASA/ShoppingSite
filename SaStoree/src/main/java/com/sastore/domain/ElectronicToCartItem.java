package com.sastore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ElectronicToCartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="electronic_id")
	private Electronic electronic;
	
	@ManyToOne
	@JoinColumn(name="electronic_cart_item_id")
	private ElectronicCartItem electroniccartItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Electronic getElectronic() {
		return electronic;
	}

	public void setElectronic(Electronic electronic) {
		this.electronic = electronic;
	}

	public ElectronicCartItem getElectroniccartItem() {
		return electroniccartItem;
	}

	public void setElectroniccartItem(ElectronicCartItem electroniccartItem) {
		this.electroniccartItem = electroniccartItem;
	}

	

	
	
}
