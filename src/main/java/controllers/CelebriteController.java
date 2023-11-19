package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import models.Celebrite;
import services.CelebriteService;

@Controller
public class CelebriteController {
	
	@Autowired
	private CelebriteService celebriteService;

	public CelebriteController(CelebriteService celebriteService) {
		this.celebriteService = celebriteService;
	}
	//---------
	
	@GetMapping("/celebrites")
	public String listCelebrites(Model model) {
		List<Celebrite> celebrites = celebriteService.getCelebrites();
		model.addAttribute("celebrites", celebrites);
		return "List_Celebrites";
	}
	//---------
	
	@GetMapping("/celebrite/new")
	public String createCelebrite(Model model) {
		Celebrite celebrite = new Celebrite();
		model.addAttribute("celebrite", celebrite);
		return "Create_Celebrite";
	}
	
	@PostMapping("/celebrite/save")
	public String saveCelebrite(@ModelAttribute("celebrite") Celebrite celebrite) {
		celebriteService.saveCelebrite(celebrite);
		return "redirect:/celebrites";
	}
	//---------

}
