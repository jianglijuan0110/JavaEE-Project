package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.CelebriteService;

@Controller
public class CelebriteController {
	
	@Autowired
	private CelebriteService celebriteService;

	public CelebriteController(CelebriteService celebriteService) {
		this.celebriteService = celebriteService;
	}

}
