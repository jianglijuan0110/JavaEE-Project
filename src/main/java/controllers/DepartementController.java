package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.Departement;
import models.Lieu;
import services.DepartementService;
import services.LieuService;

@Controller
public class DepartementController {
	
	@Autowired
	private DepartementService departementService;
	
	@Autowired
	private LieuService lieuService;

	public DepartementController(DepartementService departementService, LieuService lieuService) {
		this.departementService = departementService;
		this.lieuService = lieuService;
	}
	//---------
	
	@GetMapping("/departements")
	public String listDepartements(Model model) {
        // on recupere la liste des departements
		List<Departement> departements = departementService.getDepartements();
		model.addAttribute("departements", departements);
		return "List_Departements";
	}



	@GetMapping("/departement/new")
	public String createDepartement(Model model) {

		Departement departement = new Departement();
		model.addAttribute("departement", departement);

		model.addAttribute("departements", departementService.getDepartements());

		//model.addAttribute("lieux", lieuService.getLieux());

		return "Create_Departement";
	}


	@PostMapping("/departement/save")
	public String saveDepartement(@ModelAttribute("departement") Departement departement, Model model) {

		// Save the Departement using the service
		departementService.saveDepartement(departement);

		// Redirect to the form for Lieu with the chosen codeInsee
		return "redirect:/lieu/new/";
	}


}
