package com.sastore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sastore.domain.Book;
import com.sastore.domain.Electronic;
import com.sastore.domain.User;
import com.sastore.service.BookService;
import com.sastore.service.ElectronicService;
import com.sastore.service.UserService;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ElectronicService electronicService;

	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,@RequestParam("author") String author,@RequestParam("language") String language,
			@RequestParam("publisher") String publisher,@RequestParam("stars") String stars,@RequestParam("format") String format,
			@RequestParam("ourPrice1") String ourPrice1,@RequestParam("ourPrice2") String ourPrice2,
			Model model, Principal principal
			){
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		
		List<Book> bookList = bookService.find(category,author,language,publisher,stars,format,ourPrice1,ourPrice2);
		
		if (bookList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "bookshelf";
		}
		
		model.addAttribute("bookList", bookList);
		
		return "bookshelf";
	}
	
	@RequestMapping("/searchByECategory")
	public String searchByECategory(
			@RequestParam("category") String category,@RequestParam("brandName") String brandName,
			@RequestParam("dimension_height1") String dimension_height1,@RequestParam("dimension_height2") String dimension_height2,
			@RequestParam("dimension_width1") String dimension_width1,@RequestParam("dimension_width2") String dimension_width2,
			@RequestParam("Warranty") String Warranty,@RequestParam("stars") String stars,
			@RequestParam("ourPrice1") String ourPrice1,@RequestParam("ourPrice2") String ourPrice2,Model model, Principal principal){
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		
		List<Electronic> electronicList = electronicService.find(category,brandName,dimension_height1,dimension_height2,dimension_width1,dimension_width2,Warranty,stars,ourPrice1,ourPrice2);
		
		if (electronicList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "electronicshelf";
		}
		
		model.addAttribute("electronicList", electronicList);
		model.addAttribute("msg",category);
		return "electronicshelf";
	}
	
	@RequestMapping("/searchBook")
	public String searchBook(
			@ModelAttribute("keyword") String keyword,
			Principal principal, Model model
			) {
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Book> bookList = bookService.blurrySearch(keyword);
		List<Electronic> electronicList = electronicService.blurrySearch(keyword);
		
		if (bookList.isEmpty() && electronicList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "bookshelf";
		}
		
		if(!bookList.isEmpty() && electronicList.isEmpty())
		{
			model.addAttribute("booklist", true);
		model.addAttribute("bookList", bookList);
		}
		if(!electronicList.isEmpty() && bookList.isEmpty())
		{
			model.addAttribute("electroniclist", true);
		model.addAttribute("electronicList", electronicList);
		}
		
		if(!electronicList.isEmpty() && !bookList.isEmpty())
		{
			model.addAttribute("electronicbooklist", true);
		model.addAttribute("electronicList", electronicList);
		model.addAttribute("bookList", bookList);
		}
		
		
		return "SearchResult";
	}
}
