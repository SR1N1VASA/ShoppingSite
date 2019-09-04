package com.sastore.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sastore.domain.Electronic;

public interface ElectronicRepository extends CrudRepository<Electronic, Long>{
	List<Electronic> findByCategory(String category);
	
	List<Electronic> findBymodelNameContaining(String modelName);
}

