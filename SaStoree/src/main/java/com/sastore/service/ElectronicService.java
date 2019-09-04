package com.sastore.service;

import java.util.List;

import com.sastore.domain.Book;
import com.sastore.domain.Electronic;

public interface ElectronicService {
	
	Electronic save(Electronic electronic);

	List<Electronic> findAll();
	List<Electronic> blurrySearch(String title);
	Electronic findOne(Long id);
	List<Electronic> findByCategory(String category);
	
	void removeOne(Long id);

	List<Electronic> find(String category, String brandName, String dimension_height1, String dimension_height2,
			String dimension_width1, String dimension_width2, String warranty, String stars, String ourPrice1,
			String ourPrice2);
}
