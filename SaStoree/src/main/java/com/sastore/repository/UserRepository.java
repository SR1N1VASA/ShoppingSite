package com.sastore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sastore.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	
	User findByEmail(String email);
}
