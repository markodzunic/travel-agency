package com.travel.agency.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.travel.agency.entities.User;
import com.travel.agency.services.UserService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class LoginController {
	
	@Autowired
    private UserService korisnikService;
	
	@Autowired
    private HttpSession session;
	
	@GetMapping("/login")
    public String viewLogin() {
        return "login/login-form";
    }
	
	@PostMapping("login")
	public String processLogin(HttpServletRequest request, Model model, User korisnik) {

		korisnik = korisnikService.findByUsernamePassword(request.getParameter("username"), request.getParameter("password"));
		
		if (!BikeUtils.isEmpty(korisnik)) {
			session.setAttribute("user", korisnik);
			session.setAttribute("role", korisnik.getRole().getName());
			return "redirect:/";
		}	
	
		model.addAttribute("error", "Wrong Credentials");
		return "login/login-form";
	}
	
	@GetMapping("logout")
	public String logout(Model model) {
		session.invalidate();
		model.addAttribute("logout", "Logut");
		return "login/login-form";
	}
}
