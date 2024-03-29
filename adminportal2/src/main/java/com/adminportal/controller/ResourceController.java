package com.adminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.service.BookService;
import com.adminportal.service.ElectronicService;

@RestController
public class ResourceController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private ElectronicService electronicService;
	
	@RequestMapping(value="/book/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> bookIdList, Model model
			){
		
		for (String id : bookIdList) {
			String bookId =id.substring(8);
			bookService.removeOne(Long.parseLong(bookId));
		}
		
		return "delete success";
	}
	
	@RequestMapping(value="/electronic/removeelectronicList", method=RequestMethod.POST)
	public String removeelectronicList(
			@RequestBody ArrayList<String> electronicIdList, Model model
			){
		
		for (String id : electronicIdList) {
			String electronicId =id.substring(8);
			electronicService.removeOne(Long.parseLong(electronicId));
		}
		
		return "delete success";
	
}
}
