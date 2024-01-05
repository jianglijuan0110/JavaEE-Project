package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dto.UserRegistrationDto;
import jakarta.validation.Valid;
import models.UserEntity;
import services.UserService;

@Controller
public class AuthentificationController {
	
	private UserService userService;

	public AuthentificationController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		UserRegistrationDto user = new UserRegistrationDto();
		model.addAttribute("user", user);
		return "Register_Form";
	}
	
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") UserRegistrationDto user, 
			BindingResult result, Model model) {
		
		UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
		if (existingUserEmail != null && existingUserEmail.getEmail() != null
				&& !existingUserEmail.getEmail().isEmpty()) {
			//result.reject("email", "Il existe déjà un utilisateur avec cet email ou username");
			return "redirect:/register?fail";
		}
		
		UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
		if (existingUserUsername != null && existingUserUsername.getUsername() != null
				&& !existingUserUsername.getUsername().isEmpty()) {
			//result.reject("username", "Il existe déjà un utilisateur avec cet email ou username");
			return "redirect:/register?fail";
		}
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		
		userService.save(user);
		
		return "redirect:/monuments?success";
	}
	
	@GetMapping("/login")
	public String getLoginForm() {
		return "Login_Form";
	}
	
}
