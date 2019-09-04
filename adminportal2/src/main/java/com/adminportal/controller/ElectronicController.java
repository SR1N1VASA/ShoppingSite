package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Book;
import com.adminportal.domain.Electronic;
import com.adminportal.service.BookService;
import com.adminportal.service.ElectronicService;

@Controller
@RequestMapping("/electronic")
public class ElectronicController {

	@Autowired
	private ElectronicService electronicService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addElectronic(Model model) {
		Electronic electronic = new Electronic();
		model.addAttribute("electronic", electronic);
		return "addElectronic";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addElectronicPost(@ModelAttribute("electronic") Electronic electronic, HttpServletRequest request) {
		Boolean exists=false;
		exists=electronicService.check(electronic);
		if(exists==true)
			return "redirect:/electronic/add";
		electronicService.save(electronic);

		MultipartFile electronicImage = electronic.getElectronicImage();

		try {
			byte[] bytes = electronicImage.getBytes();
			String name = electronic.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:electronicList";
	}
	
	@RequestMapping("/electronicInfo")
	public String electronicInfo(@RequestParam("id") Long id, Model model) {
		Electronic electronic = electronicService.findOne(id);
		model.addAttribute("electronic", electronic);
		
		return "electronicInfo";
	}
	
	@RequestMapping("/updateElectronic")
	public String updateElectronic(@RequestParam("id") Long id, Model model) {
		Electronic electronic = electronicService.findOne(id);
		model.addAttribute("electronic", electronic);
		
		return "updateElectronic";
	}
	
	@RequestMapping(value="/updateElectronic", method=RequestMethod.POST)
	public String updateELectronicPost(@ModelAttribute("electronic") Electronic electronic, HttpServletRequest request) {
		electronicService.save(electronic);
		
		MultipartFile electronicImage = electronic.getElectronicImage();
		
		if(!electronicImage.isEmpty()) {
			try {
				byte[] bytes = electronicImage.getBytes();
				String name = electronic.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/image/book/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/electronic/electronicInfo?id="+electronic.getId();
	}
	

	
	
	@RequestMapping("/electronicList")
	public String electronicList(Model model) {
		List<Electronic> electronicList = electronicService.findAll();
		model.addAttribute("electronicList", electronicList);		
		return "electronicList";
		
	}
	
	@RequestMapping(value="/removeelectronic", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		electronicService.removeOne(Long.parseLong(id.substring(14)));
		List<Electronic> electronicList = electronicService.findAll();
		model.addAttribute("electronicList", electronicList);
		
		return "redirect:/electronic/electronicList";
	}
	

}
