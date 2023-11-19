package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import models.Lieu;
import services.LieuService;

@Controller
public class LieuController {
	
	@Autowired
	private LieuService lieuService;

	public LieuController(LieuService lieuService) {
		this.lieuService = lieuService;
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
		return "Create_Lieu";
	}
	
	@PostMapping("/lieu/save")
	public String saveLieu(@ModelAttribute("lieu") Lieu lieu) {
		lieuService.saveLieu(lieu);
		return "redirect:/lieux";
	}
	//---------

}
