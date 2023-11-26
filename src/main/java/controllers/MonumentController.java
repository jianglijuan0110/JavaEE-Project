package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import models.Lieu;
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
	public String saveMonument(@ModelAttribute("monument") Monument monument,  HttpSession session) {
		
		// cle etrangere codeInsee
		//Lieu lieu = lieuService.getLieuById(monument.getCodeLieu().getCodeInsee());
		
		//monument.setCodeLieu(lieu);
		
		monumentService.saveMonument(monument);
		
		// Stocker le monument dans la session
	    session.setAttribute("currentMonument", monument);
	    
		return "redirect:/celebrite/new";
	}
	//---------

}
