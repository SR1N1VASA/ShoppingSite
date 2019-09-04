package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Book;
import com.adminportal.domain.Electronic;
import com.adminportal.repository.ElectronicRepository;
import com.adminportal.service.ElectronicService;

@Service
public class ElectronicServiceImpl implements ElectronicService{
	
	@Autowired
	private ElectronicRepository electronicRepository;
	
	public Electronic save(Electronic electronic) {
		return electronicRepository.save(electronic);
	}
	
	public List<Electronic> findAll() {
		return (List<Electronic>) electronicRepository.findAll();
	}
	
	public Electronic findOne(Long id) {
		return electronicRepository.findById(id).orElse(null);
	}
	
	public void removeOne(Long id) {
		electronicRepository.deleteById(id);
	}

	@Override
	public Boolean check(Electronic electronic) {
		// TODO Auto-generated method stub
		Electronic e=electronicRepository.findByModelName(electronic.getModelName());
		if(e.getModelName().equals(electronic.getModelName()))
		return true;
		else
			return false;
	}
}
