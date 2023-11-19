package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import models.Monument;
import services.MonumentService;

@Controller
public class MonumentController {
	
	@Autowired
	private MonumentService monumentService;

	public MonumentController(MonumentService monumentService) {
		this.monumentService = monumentService;
	}
	//---------
	
	@GetMapping("/monuments")
	public String listMonuments(Model model) {
		List<Monument> monuments = monumentService.getMonuments();
		model.addAttribute("monuments", monuments);
		return "List_Monuments";
	}
	//---------
	
	@GetMapping("/monument/new")
	public String createMonument(Model model) {
		Monument monument = new Monument();
		model.addAttribute("monument", monument);
		return "Create_Monument";
	}
	
	@PostMapping("/monument/save")
	public String saveMonument(@ModelAttribute("monument") Monument monument) {
		monumentService.saveMonument(monument);
		return "redirect:/monuments";
	}
	//---------

}
