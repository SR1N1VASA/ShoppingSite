package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Electronic;

public interface ElectronicService {
	
	Electronic save(Electronic electronic);

	List<Electronic> findAll();
	
	Electronic findOne(Long id);
	
	void removeOne(Long id);

	Boolean check(Electronic electronic);
}
