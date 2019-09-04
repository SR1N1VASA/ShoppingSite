package com.sastore.service;

import com.sastore.domain.ShippingAddress;
import com.sastore.domain.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
