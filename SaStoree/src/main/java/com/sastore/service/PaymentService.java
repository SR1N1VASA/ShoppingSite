package com.sastore.service;

import com.sastore.domain.Payment;
import com.sastore.domain.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
