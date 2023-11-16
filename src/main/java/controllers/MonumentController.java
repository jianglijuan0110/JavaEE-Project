package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import models.Monument;
import services.MonumentService;

@Controller
public class MonumentController {
	
	@Autowired
	private MonumentService monumentService;

	public MonumentController(MonumentService monumentService) {
		this.monumentService = monumentService;
	}
	
	@GetMapping("/monuments")
	public String listMonuments(Model model) {
		List<Monument> monuments = monumentService.getMonuments();
		model.addAttribute("monuments", monuments);
		return "List_Monuments";
	}

}
