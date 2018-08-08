package com.travel.agency.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travel.agency.entities.Role;
import com.travel.agency.entities.User;
import com.travel.agency.services.RoleService;
import com.travel.agency.services.UserService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class LoginController {
	
	@Autowired
    private UserService korisnikService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
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
	
	
	@GetMapping(value = "/register")
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		
		List<Role> roles = roleService.findAll();

		model.addObject("roles", roles);
		
		model.addObject("user", user);
		model.setViewName("login/register");
		return model;
	}
	
	
	
	@PostMapping("/register")
	public String create(@Valid @ModelAttribute User u, BindingResult bd, Model model) {
		
		
		List<Role> roles = roleService.findAll();

		model.addAttribute("roles", roles);
	
			
		if (bd.hasErrors()) {
			model.addAttribute("user", u);
			model.addAttribute("path", "/register");
            return "login/register";
        }
		
		userService.save(u);
		model.addAttribute("user", u);
		
		return "redirect:/";
	}
	
	
}
