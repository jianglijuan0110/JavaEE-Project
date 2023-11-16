package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import models.Lieu;
import services.LieuService;

@Controller
public class LieuController {
	
	@Autowired
	private LieuService lieuService;

	public LieuController(LieuService lieuService) {
		this.lieuService = lieuService;
	}
	
	@GetMapping("/lieux")
	public String listMonuments(Model model) {
		List<Lieu> lieux = lieuService.getLieux();
		model.addAttribute("lieux", lieux);
		return "List_Lieux";
	}

}
