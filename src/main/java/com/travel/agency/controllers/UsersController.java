package com.travel.agency.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.agency.entities.User;
import com.travel.agency.services.UserService;

@Controller
public class UsersController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("users")
	public String users(Model model) {
		
		List <User> user = userService.findAll();
		model.addAttribute("users", user);
		
		return "users/users";
	}
	
	@RequestMapping("users/{id}")
	public String user(@PathVariable("id") int id, Model model) {
		
		User user = userService.readById(id);
		model.addAttribute("user", user);
		
		return "users/user-profile";
	}
	
	@RequestMapping("users/delete")
	public String delete(HttpServletRequest request, Model model) {
		User user = userService.readById(2);

//		if (request.getMethod() == "GET") {
			model.addAttribute("user", user);	
			return "users/user-dialog";
//		} else {
//			userService.delete(user);
//			return "redirect: /users";
//		}
	}
}
