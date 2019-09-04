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
import com.sastore.domain.CartItem;
import com.sastore.domain.Electronic;
import com.sastore.domain.ElectronicCartItem;
import com.sastore.domain.ShoppingCart;
import com.sastore.domain.User;
import com.sastore.service.BookService;
import com.sastore.service.CartItemService;
import com.sastore.service.ElectronicCartItemService;
import com.sastore.service.ElectronicService;
import com.sastore.service.ShoppingCartService;
import com.sastore.service.UserService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ElectronicService electronicService;
	
	@Autowired
	private ElectronicCartItemService electroniccartitemservice;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		System.out.println("man");
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		shoppingCartService.updateShoppingCart(shoppingCart);
		List<ElectronicCartItem> electroniccartItemList = electroniccartitemservice.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("electroniccartItemList", electroniccartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
	}

	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("book") Book book,
			@ModelAttribute("qty") String qty,
			Model model, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		book = bookService.findOne(book.getId());
		System.out.println("--------------"+book.getInStockNumber());
		
		if (Integer.parseInt(qty) > book.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/bookDetail?id="+book.getId();
		}
		
		CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
		model.addAttribute("addBookSuccess", true);
		
		return "forward:/bookDetail?id="+book.getId();
	}
	
	@RequestMapping("/addelectronicItem")
	public String addelectronicItem(
			@ModelAttribute("electronic") Electronic electronic,
			@ModelAttribute("qty") String qty,
			Model model, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		electronic = electronicService.findOne(electronic.getId());
		System.out.println("--------------"+electronic.getInStockNumber());
		
		if (Integer.parseInt(qty) > electronic.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/electronicDetail?id="+electronic.getId();
		}
		
		ElectronicCartItem electroniccartItem = electroniccartitemservice.addElectronicToCartItem(electronic, user, Integer.parseInt(qty));
		model.addAttribute("addElectronicSuccess", true);
		
		return "forward:/electronicDetail?id="+electronic.getId();
	}
	
	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(
			@ModelAttribute("id") Long cartItemId,
			@ModelAttribute("qty") int qty, Model model
			) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		Book book=cartItem.getBook();
		if (qty > book.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/shoppingCart/cart";
		}
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);
		model.addAttribute("addBookSuccess", true);
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/updateECartItem")
	public String updateEShoppingCart(
			@ModelAttribute("id") Long electroniccartItemId,
			@ModelAttribute("qty") int qty, Model model
			) {
		ElectronicCartItem electroniccartItem = electroniccartitemservice.findById(electroniccartItemId);
		Electronic electronic=electroniccartItem.getElectronic();
		if (qty > electronic.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/shoppingCart/cart";
		}
		electroniccartItem.setQty(qty);
		electroniccartitemservice.updateECartItem(electroniccartItem);
		model.addAttribute("addElectronicSuccess", true);
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/removeEItem")
	public String removeEItem(@RequestParam("id") Long id) {
		electroniccartitemservice.removeECartItem(electroniccartitemservice.findById(id));
		
		return "forward:/shoppingCart/cart";
	}
}
