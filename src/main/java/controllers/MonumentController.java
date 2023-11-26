package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import models.Monument;
import services.DepartementService;
import services.LieuService;
import services.MonumentService;

@Controller
public class MonumentController {
	
	@Autowired
	private MonumentService monumentService;
	
	@Autowired
	private DepartementService departementService;
	
	@Autowired
	private LieuService lieuService;

	public MonumentController(MonumentService monumentService, LieuService lieuService) {
		this.monumentService = monumentService;
		this.lieuService = lieuService;
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
		
		model.addAttribute("lieux", lieuService.getLieux());
		
		model.addAttribute("departements", departementService.getDepartements());
		
		return "Create_Monument";
	}
	
	@PostMapping("/monument/save")
	public String saveMonument(@ModelAttribute("monument") Monument monument, HttpSession session) {
		
		monumentService.saveMonument(monument);
		
		// Récupérer le monument créé avec son ID
	    Monument createdMonument = monumentService.getMonumentById(monument.getGeohash());

	    // Ajouter l'ID du monument à la session
	    session.setAttribute("monumentId", createdMonument.getGeohash());
	    
		return "redirect:/celebrite/new";
	}
	//---------

}
