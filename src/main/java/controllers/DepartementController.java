package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.DepartementService;

@Controller
public class DepartementController {
	
	@Autowired
	private DepartementService departementService;

	public DepartementController(DepartementService departementService) {
		this.departementService = departementService;
	}

}
