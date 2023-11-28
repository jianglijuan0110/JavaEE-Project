package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

	public CelebriteController(CelebriteService celebriteService, MonumentService monumentService) {
        this.celebriteService = celebriteService;
        this.monumentService = monumentService;
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
		
		// Récupérer l'ID du monument à partir de la session
	    String id = (String) session.getAttribute("monumentId");
	    
	    Monument monument = monumentService.getMonumentById(id);
	    model.addAttribute("monument", monument);
	    
		Celebrite celebrite = new Celebrite();    
	    model.addAttribute("celebrite", celebrite);
	    
		return "Create_Celebrite";
	}
	
	@PostMapping("/celebrite/save")
	public String saveCelebrite(@ModelAttribute("celebrite") Celebrite celebrite, HttpSession session, Model model) {
		
		// Récupérer l'ID du monument à partir de la session
	    String id = (String) session.getAttribute("monumentId");
	    
		celebriteService.saveCelebrite(celebrite);
		
		celebriteService.associateMonumentWithCelebrite(id, celebrite.getNumCelebrite());
		
		// Supprimer l'ID du monument de la session une fois utilisé
	    session.removeAttribute("monumentId");
	    
		return "redirect:/celebrites";
	}
	//---------

}
