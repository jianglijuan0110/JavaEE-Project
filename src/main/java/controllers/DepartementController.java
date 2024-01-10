package controllers;

import java.util.List;
import models.Lieu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/departement/{dep}/edit")
	public String editDepartement(@PathVariable("dep") String dep, Model model) {
		Departement departement = departementService.getDepartementById(dep);
		model.addAttribute("departement", departement);
		return "Edit_Departement";
	}

	@PostMapping("/departement/{dep}/update")
	public String updateDepartement(@PathVariable("dep") String dep, @ModelAttribute("departement") Departement departement) {
		departementService.updateDepartement(departement,dep);
		return "redirect:/departements";
	}
	@GetMapping("/departement/{dep}/delete")
	public String deleteDepartement(@PathVariable("dep") String dep) {

		departementService.deleteDepartement(dep);
		return "redirect:/departements";

	}
	
}
