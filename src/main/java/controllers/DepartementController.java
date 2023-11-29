package controllers;

import java.util.List;
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
<<<<<<< HEAD



=======
	//---------
	
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
	@GetMapping("/departement/new")
	public String createDepartement(Model model) {
		
		Departement departement = new Departement();
		model.addAttribute("departement", departement);
<<<<<<< HEAD
		Lieu chefLieu = new Lieu();
		model.addAttribute("chefLieu", chefLieu);

=======
		
		Lieu chefLieu = new Lieu();
		model.addAttribute("chefLieu", chefLieu);
		
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
		return "Create_Departement";
	}


	@PostMapping("/departement/save")
<<<<<<< HEAD

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

=======
	public String saveDepartement(@ModelAttribute("departement") Departement departement, 
			@ModelAttribute("chefLieu") Lieu chefLieu) {
		
		departementService.saveDepartement(
                departement,
                chefLieu.getCodeInsee(),
                chefLieu.getNomCom(),
                chefLieu.getLongitude(),
                chefLieu.getLatitude()
        );
		
		// Redirection
		return "redirect:/departements";
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
	}
	
}
