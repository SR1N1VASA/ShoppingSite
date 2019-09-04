package com.sastore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sastore.domain.Book;
import com.sastore.domain.Electronic;
import com.sastore.repository.ElectronicRepository;
import com.sastore.service.ElectronicService;

@Service
public class ElectronicServiceImpl implements ElectronicService{
	
	@Autowired
	private ElectronicRepository electronicRepository;
	
	public Electronic save(Electronic electronic) {
		return electronicRepository.save(electronic);
	}
	
	public List<Electronic> findAll() {
		List<Electronic> electronicList = (List<Electronic>) electronicRepository.findAll();
		List<Electronic> activeElectronicList = new ArrayList<>();
		
		for (Electronic electronic: electronicList) {
			if(electronic.isActive()) {
				activeElectronicList.add(electronic);
			}
		}
		
		return activeElectronicList;
	}
	public List<Electronic> findByCategory(String category){
		List<Electronic> electronicList = electronicRepository.findByCategory(category);
		
		List<Electronic> activeElectronicList = new ArrayList<>();
		
		for (Electronic electronic: electronicList) {
			if(electronic.isActive()) {
				activeElectronicList.add(electronic);
			}
		}
		
		return activeElectronicList;
	}
	
	public Electronic findOne(Long id) {
		return electronicRepository.findById(id).orElse(null);
	}
	
	public void removeOne(Long id) {
		electronicRepository.deleteById(id);
	}
	
	public List<Electronic> blurrySearch(String title) {
		List<Electronic> electronicList = electronicRepository.findBymodelNameContaining(title);
List<Electronic> activeElectronicList = new ArrayList<>();
		
		for (Electronic electronic: electronicList) {
			if(electronic.isActive()) {
				activeElectronicList.add(electronic);
			}
		}
		
		return activeElectronicList;
	}

	@Override
	public List<Electronic> find(String category, String brandName, String dimension_height1, String dimension_height2,
			String dimension_width1, String dimension_width2, String warranty, String stars, String ourPrice1,
			String ourPrice2) {
		// TODO Auto-generated method stub
		List<Electronic> electronicList = (List<Electronic>) electronicRepository.findAll();
		List<Electronic> activeElectronicList = new ArrayList<>();
		int star;
		if(stars.equals("All"))
			star=0;
		else
			star = Integer.parseInt(stars);
		int Price1,Price2;
		if(ourPrice1.equals("") || ourPrice2.equals(""))
		{
			ourPrice1="All";
			Price1=0;
			ourPrice2="All";
			Price2=0;
		}
		else
		{
		Price1 = Integer.parseInt(ourPrice1);
		Price2 = Integer.parseInt(ourPrice2);
		}
		int height1,height2,width1,width2,Warranty;
		if(dimension_height1.equals("All"))
			height1=0;
		else
			height1 = Integer.parseInt(dimension_height1);
		if(dimension_height2.equals("All"))
			height2=0;
		else
			height2 = Integer.parseInt(dimension_height2);
		if(dimension_width1.equals("All"))
			width1=0;
		else
			width1 = Integer.parseInt(dimension_width1);
		if(dimension_width2.equals("All"))
			width2=0;
		else
			width2 = Integer.parseInt(dimension_width2);
		if(warranty.equals("All"))
			Warranty=0;
		else
			Warranty = Integer.parseInt(warranty);
		for (Electronic electronic: electronicList) {
			if((electronic.getCategory().equals(category) || category.equals("All")) && (electronic.getBrandName().equals(brandName) || brandName.equals("All"))) 
					{
								 if(electronic.getStars()>=star || stars.equals("All")) 	 
								{ 
									 if(electronic.getWarranty()>=Warranty || warranty.equals("All")) 
									 {
									 
									if(electronic.getOurPrice()>=Price1 || ourPrice1.equals("All"))
									{
									if(electronic.getOurPrice()<=Price2 || ourPrice2.equals("All"))
										{
										if(electronic.getDimension_height()>=height1 || dimension_height1.equals("All"))
										{
										if(electronic.getDimension_height()<=height2 || dimension_height2.equals("All"))
											{
											if(electronic.getDimension_width()>=width1 || dimension_width1.equals("All"))
											{
											if(electronic.getDimension_width()<=width2 || dimension_width2.equals("All"))
												{
											activeElectronicList.add(electronic);
											
										}
									}
								}	
							 }		
						}
									
						}
	
					}
					}
					}
		}
	
		return activeElectronicList;
	}
	
	
}
