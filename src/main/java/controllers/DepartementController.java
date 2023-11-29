package controllers;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import models.Lieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import models.Departement;
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
		Lieu chefLieu = new Lieu();
		model.addAttribute("chefLieu", chefLieu);

		return "Create_Departement";
	}


	@PostMapping("/departement/save")

	public String saveDepartement(@ModelAttribute("departement") Departement departement,
								  @ModelAttribute("chefLieu") Lieu chefLieu) {

		String codeInseeChefLieu = chefLieu.getCodeInsee();
		String nomCom = chefLieu.getNomCom();
		double longitude = chefLieu.getLongitude();
		double latitude = chefLieu.getLatitude();

		// Save the Departement using the service
		departementService.saveDepartement(departement,codeInseeChefLieu,nomCom,longitude,latitude);


		// Redirect to the form for Lieu with the chosen codeInsee

		return "redirect:/lieux";

	}


}
