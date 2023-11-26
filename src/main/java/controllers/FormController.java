package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import models.Celebrite;
import models.Departement;
import models.Lieu;
import models.Monument;
import services.CelebriteService;
import services.DepartementService;
import services.LieuService;
import services.MonumentService;

@Controller
public class FormController {
	
	@Autowired
	private DepartementService departementService;
	
	@Autowired
	private LieuService lieuService;
	
	@Autowired
	private MonumentService monumentService;
	
	@Autowired
	private CelebriteService celebriteService;
	
	public FormController(MonumentService monumentService, LieuService lieuService, 
			DepartementService departementService, CelebriteService celebriteService) {
		this.monumentService = monumentService;
		this.lieuService = lieuService;
		this.departementService = departementService;
		this.celebriteService = celebriteService;
	}
	
	@GetMapping("/form")
	public String createMonument(Model model) {
		
		Monument monument = new Monument();
		model.addAttribute("monument", monument);
		
		Departement departement = new Departement();
		model.addAttribute("departement", departement);
		
		Lieu lieu = new Lieu();
		model.addAttribute("lieu", lieu);
		
		Celebrite celebrite = new Celebrite();
		model.addAttribute("celebrite", celebrite);
		
		return "Create_Global";
	}
	
	@GetMapping("/form/save")
	public String saveMonument(Model model) {
		
		return "";
	}


}
