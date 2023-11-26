package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import models.Celebrite;
import models.Monument;
import services.CelebriteService;
import services.MonumentService;

@Controller
public class CelebriteController {
	
	@Autowired
	private CelebriteService celebriteService;
	
	@Autowired
	private MonumentService monumentService;

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
	public String createCelebrite(Model model, HttpSession session) {
		Celebrite celebrite = new Celebrite();
	    model.addAttribute("celebrite", celebrite);

	    // Récupérer le monument depuis la session
	    Monument currentMonument = (Monument) session.getAttribute("currentMonument");
	    model.addAttribute("currentMonument", currentMonument);
	    
		return "Create_Celebrite";
	}
	
	@PostMapping("/celebrite/save")
	public String saveCelebrite(@ModelAttribute("celebrite") Celebrite celebrite, @RequestParam("geohash") String geohash, HttpSession session) {
		
		// Récupérer le monument complet depuis la base de données
	    Monument monument = monumentService.getMonumentById(geohash);

	    // Associer la célébrité au monument
	    celebrite.getMonuments().add(monument);
	    monument.getCelebrites().add(celebrite);
	    
		celebriteService.saveCelebrite(celebrite);
		
		// Effacer le monument de la session après utilisation
	    session.removeAttribute("currentMonument");
		
		return "redirect:/monuments";
	}
	//---------

}
