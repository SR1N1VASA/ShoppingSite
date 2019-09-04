package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Electronic;

public interface ElectronicRepository extends CrudRepository<Electronic, Long>{

	Electronic findByModelName(String modelName);

}
