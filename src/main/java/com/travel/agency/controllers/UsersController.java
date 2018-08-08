package com.travel.agency.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agency.entities.Role;
import com.travel.agency.entities.User;
import com.travel.agency.services.RoleService;
import com.travel.agency.services.UserService;

@Controller
public class UsersController {
	
	@Autowired
	BCryptPasswordEncoder bcEncoder;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	ObjectMapper mapper;

	@RequestMapping("users")
	public String users(Model model) {

		List<User> user = userService.findAll();
		model.addAttribute("users", user);

		return "users/users";
	}

	@RequestMapping("users/{id}")
	public String user(@PathVariable("id") int id, Model model) {

		User user = userService.readById(id);
		model.addAttribute("user", user);

		return "users/user-profile";
	}

	@GetMapping("users/delete")
	public String deleteDialog(HttpServletRequest request, Model model) {
		User user = userService.readById(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("user", user);

		return "users/user-dialog";
	}

	@RequestMapping("users/refresh")
	public String refresh(HttpServletRequest request, Model model) {
		List<User> user = userService.findAll();
		model.addAttribute("users", user);

		return "users/users-table";
	}

	@PostMapping(path = "users/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User delete(HttpServletRequest request, Model model) {
		User user = userService.readById(Integer.valueOf(request.getParameter("id")));
		userService.delete(user);

		return user;
	}

	@GetMapping(path = "users/update/{id}")
	public String userForm(@PathVariable("id") int id, Model model) {
		List<Role> r = roleService.findAll();
		User u = userService.readById(Integer.valueOf(id));
		model.addAttribute("user", u);
		model.addAttribute("roles", r);
		model.addAttribute("path", "users/update/" + id);
		return "users/user-form";

	}

	@PostMapping("users/update/{id}")
	public String update(@ModelAttribute User u, BindingResult bd, @PathVariable("id") int id, Model model) {

		if (bd.hasFieldErrors()) {
			model.addAttribute("path", "users/update/" + id);
			return "users/user-form";
		}

		List<Role> roles = roleService.findAll();

		model.addAttribute("roles", roles);
		userService.update(u);
		model.addAttribute("user", u);

		return "redirect:/users/" + id;
	}

	@GetMapping("users/create")
	public String userCreate(Model model) {
		User u = new User();
		List<Role> roles = roleService.findAll();

		model.addAttribute("roles", roles);
		model.addAttribute("user", u);
		model.addAttribute("path", "/users/create");

		return "users/user-form";

	}
	
	@PostMapping("users/create")
	public String create(@Valid @ModelAttribute User u, BindingResult bd, Model model) {
		
		List<Role> roles = roleService.findAll();
		
		if (bd.hasErrors()) {
			model.addAttribute("user", u);
			model.addAttribute("path", "users/create");
            return "users/user-form";
        }
		
		u.setPassword(bcEncoder.encode(u.getPassword()));
		u.setActive(1);
		userService.save(u);
		model.addAttribute("user",u);
		
		return "redirect:/users";
					
				
	}

}
