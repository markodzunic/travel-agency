package com.travel.agency.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.travel.agency.entities.User;
import com.travel.agency.services.UserService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class UsersController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ObjectMapper mapper;
	
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
	
	@GetMapping("users/delete")
	public String deleteDialog(HttpServletRequest request, Model model) {
		User user = userService.readById(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("user", user);	
		
		return "users/user-dialog";
	}
	
	@RequestMapping("users/refresh")
	public String refresh(HttpServletRequest request, Model model) {
		List <User> user = userService.findAll();
		model.addAttribute("users", user);	
		
		return "users/users-table";
	}
	
	@PostMapping(path="users/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User delete(HttpServletRequest request, Model model) {
		User user = userService.readById(Integer.valueOf(request.getParameter("id")));

        return user;
	}
}
