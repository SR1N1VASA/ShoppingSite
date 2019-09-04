package com.sastore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sastore.domain.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long>{

}
