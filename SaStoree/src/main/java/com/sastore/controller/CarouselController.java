package com.sastore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sastore.domain.Book;
import com.sastore.domain.Electronic;
import com.sastore.service.ElectronicService;

@Controller
public class CarouselController {

	@Autowired
	private ElectronicService electronicService;
	
	@RequestMapping("/honormobiles")
	public String honormobiles(Model model) {
		List<Electronic> electronicList = electronicService.findAll();
		List<Electronic> carouselList=new ArrayList();
		for (Electronic electronic: electronicList) {
			if((electronic.getBrandName()).equals("Honor") && (electronic.getCategory()).equals("Mobile")) {
				carouselList.add(electronic);
			}
		}
		model.addAttribute("electronicList", carouselList);
		model.addAttribute("msg","Honor Mobiles");
		
		return "electronicshelf";
	}
	
	@RequestMapping("/canoncameras")
	public String canoncameras(Model model, Principal principal) {
		List<Electronic> electronicList = electronicService.findAll();
		List<Electronic> carouselList=new ArrayList();
		for (Electronic electronic: electronicList) {
			if((electronic.getBrandName()).equals("Canon") && (electronic.getCategory()).equals("Cameras")) {
				carouselList.add(electronic);
			}
		}
		model.addAttribute("electronicList", carouselList);
		model.addAttribute("msg","Canon Cameras");
		return "electronicshelf";

		
	}
	
	@RequestMapping("/googleproducts")
	public String googleproducts(Model model, Principal principal) {
		List<Electronic> electronicList = electronicService.findAll();
		List<Electronic> carouselList=new ArrayList();
		for (Electronic electronic: electronicList) {
			if((electronic.getBrandName()).equals("Google")) {
				carouselList.add(electronic);
			}
		}
		model.addAttribute("electronicList", carouselList);
		model.addAttribute("msg","Google Products");
		return "electronicshelf";

		
	}
	
}
