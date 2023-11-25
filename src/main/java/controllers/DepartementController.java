package controllers;

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
	//---------
	
	@GetMapping("/departement/new/{insertedDep}")
	public String createDepartement(@PathVariable("insertedDep") String insertedDep, Model model) {
		
		// On ajoute la valeur du département au modèle pour le formulaire departementForm
	    model.addAttribute("insertedDep", insertedDep);
		
        /*Departement departement = new Departement();
	    departement.setDep(insertedDep); // Préremplir le champ dep avec la valeur insertedDep
		model.addAttribute("departement", departement);*/
		
		//model.addAttribute("lieux", lieuService.getLieux());
		
		return "Create_Departement";
	}
	
	@PostMapping("/departement/save")
	public String saveDepartement(@ModelAttribute("departement") Departement departement,
			@RequestParam("insertedDep") String insertedDep) {
		
		// Departement associé à insertedDep
		/*Departement dept = departementService.getDepartementById(insertedDep);
		
		if(dept == null) {
			
			Lieu chefLieu = new Lieu();
			departement.setChefLieu(chefLieu);
			
			departementService.saveDepartement(departement);
		}*/
		
		// lieu associe a la cle etrangere codeInsee
		//Lieu lieu = lieuService.getLieuById(departement.getChefLieu().getCodeInsee());
		
		
		
		// Faire la redirection vers le formulaire pour monument
		return "redirect:/monument/new";
	}
	//---------

}
