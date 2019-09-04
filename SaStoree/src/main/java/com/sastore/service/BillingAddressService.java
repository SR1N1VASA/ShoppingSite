package com.sastore.service;

import com.sastore.domain.BillingAddress;
import com.sastore.domain.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
