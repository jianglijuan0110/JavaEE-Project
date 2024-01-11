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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import models.Celebrite;
import models.Monument;
import services.DepartementService;
import services.LieuService;
import services.MonumentService;

@Controller
public class MonumentController {
	
	@Autowired
	private MonumentService monumentService;
	
	@Autowired
	private DepartementService departementService;
	
	@Autowired
	private LieuService lieuService;

	public MonumentController(MonumentService monumentService, LieuService lieuService) {
		this.monumentService = monumentService;
		this.lieuService = lieuService;
	}
	
	//---------LISTE DES MONUMENTS
	
	@GetMapping("/monuments")
	public String listMonuments(Model model) {
		List<Monument> monuments = monumentService.getMonuments();
		model.addAttribute("monuments", monuments);
		return "List_Monuments";
	}
	
	//---------CREER UN NOUVEAU MONUMENT
	
	@GetMapping("/monument/new")
	public String createMonument(Model model) {
		
		Monument monument = new Monument();
		model.addAttribute("monument", monument);
		
		model.addAttribute("lieux", lieuService.getLieux());
		
		model.addAttribute("departements", departementService.getDepartements());
		
		return "Create_Monument";
	}
	
	@PostMapping("/monument/save")
	public String saveMonument(@ModelAttribute("monument") Monument monument, HttpSession session) {
		
		monumentService.saveMonument(monument);
		
		// Récupérer le monument créé avec son ID
	    Monument createdMonument = monumentService.getMonumentById(monument.getGeohash());

	    // Ajouter l'ID du monument à la session
	    session.setAttribute("monumentId", createdMonument.getGeohash());
	    
		return "redirect:/celebrite/new";
	}
	
	//---------MODIFIER UN MONUMENT
	
	@GetMapping("/monument/{numMonum}/edit")
	public String editMonument(@PathVariable("numMonum") String numMonum, Model model) {
		Monument monument = monumentService.getMonumentById(numMonum);
		model.addAttribute("monument", monument);
		model.addAttribute("departements", departementService.getDepartements());
		return "Edit_Monument";
	}
	
	@PostMapping("/monument/{numMonum}/update")
	public String updateMonument(@PathVariable("numMonum") String numMonum, @ModelAttribute("monument") Monument monument) {
		monumentService.updateMonument(monument, numMonum);
		return "redirect:/monuments";
	}
	
	//---------SUPPRIMER UN MONUMENT
	
	@GetMapping("/monument/{numMonum}/delete")
	public String deleteMonument(@PathVariable("numMonum") String numMonum) {
		monumentService.deleteMonument(numMonum);
		return "redirect:/monuments";
	}
	
	//---------RECHERCHER UN MONUMENT
	
	@GetMapping("/monument/searchNom")
	public String searchMonumentByNom(HttpServletRequest request, Model model) {
		
		String nom = request.getParameter("nom");
		
		List<Monument> monuments = monumentService.searchMonumentByNom(nom);
		model.addAttribute("monuments", monuments);
		
		return "Search_MonumentByNom";
	}
	
	@GetMapping("/monument/searchLieu")
	public String searchMonumentByLieu(HttpServletRequest request, Model model) {
		
		String lieu = request.getParameter("lieu");
		
		List<Monument> monuments = monumentService.searchMonumentByLieu(lieu);
		model.addAttribute("monuments", monuments);
		
		return "Search_MonumentByLieu";
	}
	
	@GetMapping("/monument/searchDept")
	public String searchMonumentByDepartement(HttpServletRequest request, Model model) {
		
		String dep = request.getParameter("dep");
		
		List<Monument> monuments = monumentService.searchMonumentByDepartement(dep);
		model.addAttribute("monuments", monuments);
		
		return "Search_MonumentByDept";
	}
	
	@GetMapping("/monuments/search")
	public String searchMonument(@RequestParam(value="query") String query, Model model) {
		
		List<Monument> monuments = monumentService.searchMonuments(query);
		model.addAttribute("monuments", monuments);
		
		return "List_Monuments";
	}
	
	//---------CALCUL DE DISTANCE
	
	@GetMapping("/calculeD")
	public String calculDistanceBetweenMonuments(@RequestParam(name = "m1", required = false) String geohash1,
												 @RequestParam(name = "m2", required = false) String geohash2,
												 Model model) {
		List<Monument> monuments = monumentService.getMonuments();
		model.addAttribute("monuments", monuments);

		if (geohash1 != null && geohash2 != null) {
			Monument m1 = monumentService.getMonumentById(geohash1);
			Monument m2 = monumentService.getMonumentById(geohash2);
			double distance = 0;

			if (!m1.equals(m2)) {
				distance = monumentService.calculeDistance(m1, m2);
			}

			model.addAttribute("distance", distance);
			model.addAttribute("m1", m1);
			model.addAttribute("m2", m2);
		}
		
		return "Calcul_distance_monuments";
	}
	
	@GetMapping("/monument/{numMonum}/details")
    public String viewMonumentDetails(@PathVariable("numMonum") String numMonum, Model model, HttpSession session) {
        Monument monument = monumentService.getMonumentById(numMonum);
        List<Celebrite> celebrites = monument.getCelebrites();

        model.addAttribute("monument", monument);
        model.addAttribute("celebrites", celebrites);
        
        // Ajouter l'ID du monument à la session
	    session.setAttribute("monumentId", numMonum);

        return "monumentDetails";
    }

}
