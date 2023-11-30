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
@Controller
public class DepartementController {
	
	@Autowired
	private DepartementService departementService;

	public DepartementController(DepartementService departementService) {
		this.departementService = departementService;
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
		
		departementService.saveDepartement(
                departement,
                chefLieu.getCodeInsee(),
                chefLieu.getNomCom(),
                chefLieu.getLongitude(),
                chefLieu.getLatitude()
        );
		
		// Redirection
		return "redirect:/departements";

	}
	
}
