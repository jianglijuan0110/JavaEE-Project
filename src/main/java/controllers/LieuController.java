package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import models.Lieu;
import services.DepartementService;
import services.LieuService;
import services.MonumentService;

@Controller
public class LieuController {

	@Autowired
	private LieuService lieuService;

	@Autowired
	private DepartementService departementService;

	@Autowired
	private MonumentService monumentService;

	public LieuController(LieuService lieuService, DepartementService departementService, MonumentService monumentService) {
		this.lieuService = lieuService;
		this.departementService = departementService;
		this.monumentService = monumentService;
	}
	//---------

	@GetMapping("/lieux")
	public String listLieux(Model model) {
		List<Lieu> lieux = lieuService.getLieux();
		model.addAttribute("lieux", lieux);
		return "List_Lieux";
	}
	//---------

	@GetMapping("/lieu/new")
	public String createLieu(Model model) {

		Lieu lieu = new Lieu();
		model.addAttribute("lieu", lieu);

		model.addAttribute("departements", departementService.getDepartements());
		
		//model.addAttribute("monuments", monumentService.getMonuments());
		

		return "Create_Lieu";
	}
	@PostMapping("/lieu/save")
	public String enregistreLieu(@ModelAttribute("lieu") Lieu lieu) {

		// Save the Lieu using the service
		lieuService.saveLieu(lieu);

		// Redirect to the form
		return "redirect:/lieux";
	}


}
