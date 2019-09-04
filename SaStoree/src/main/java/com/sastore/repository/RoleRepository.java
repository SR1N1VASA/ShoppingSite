package com.sastore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sastore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
