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
	//---------
<<<<<<< HEAD
	
	@GetMapping("/departement/new")
	public String createDepartement(Model model) {
		
		Departement departement = new Departement();
		model.addAttribute("departement", departement);
		
		model.addAttribute("departements", departementService.getDepartements());
		
		//model.addAttribute("lieux", lieuService.getLieux());
		


	@GetMapping("/departement/new")
	public String createDepartement(Model model) {

		Departement departement = new Departement();
		model.addAttribute("departement", departement);

		return "Create_Departement";
	}


	@PostMapping("/departement/save")

	public String saveDepartement(@ModelAttribute("departement") Departement departement, Model model) {
	public String saveDepartement(@ModelAttribute("departement") Departement departement,
								  @RequestParam String chefLieuId,
								  @RequestParam String nomDep,
								  @RequestParam String reg,
								  @RequestParam String dep, Model model) {

		// Retrieve the Lieu object by its ID
		Lieu chefLieu = lieuService.getLieuById(chefLieuId);

		// Set the values for the Departement
		departement.setChefLieu(chefLieu);
		departement.setNomDep(nomDep);
		departement.setReg(reg);
		departement.setDep(dep);

		// Check if chefLieu is null
		if (chefLieu == null) {
			// If the chefLieu doesn't exist, create a new one and set its values
			chefLieu = new Lieu();
			chefLieu.setNomCom("Set the name as needed");
			chefLieu.setLongitude(0.0);  // Set the longitude as needed
			chefLieu.setLatitude(0.0);   // Set the latitude as needed

			// Set the Departement for the chefLieu
			chefLieu.setDepartement(departement);

			// Add the chefLieu to the Departement's list of Lieux
			List<Lieu> lieux = new ArrayList<>();
			lieux.add(chefLieu);
			departement.setLieux(lieux);
		} else {
			// If chefLieu exists, just set the Departement for the existing chefLieu
			chefLieu.setDepartement(departement);

			// Check if the Departement's list of Lieux is null
			if (departement.getLieux() == null) {
				List<Lieu> lieux = new ArrayList<>();
				lieux.add(chefLieu);
				departement.setLieux(lieux);
			} else {
				// If the list is not null, add the chefLieu to the existing list
				departement.getLieux().add(chefLieu);
			}
		}


		// Save the Departement using the service
		departementService.saveDepartement(departement);

		// Redirect to the form for Lieu with the chosen codeInsee
		return "redirect:/monument/new/";
	}


}
