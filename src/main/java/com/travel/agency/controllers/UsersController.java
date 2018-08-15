///**
//* ─────────────────────────────────────────────────────────────────────────────────────────────────┐
//* Users controller class designed to handle user related pages.
//*
//* [Additional information about this class should be added here, if available. Add a single line
//* break between the summary and the additional info.  Use as many lines as necessary. Make sure to
//* leave a line break between the class description and the URL Mapping, below.]
//*
//* URL Mapping:  [GET] - /users
//* 				[GET, POST] - /users/create
//* 				[GET, POST] - /users/update/{id}
//* 				[GET, POST] - /users/delete
//* 				[GET] - /users/{id}
//* 				[POST] - /users/refresh
//* 
//* ──────────────────────────────────────────────────────────────────────────────────────────────────
//* @author         KUNA   <soul6reaver@gmail.com>
//* @modifiedBy     KUNA   <soul6reaver@gmail.com>
//* @maintainedBy   KUNA   <soul6reaver@gmail.com>
//* @version        1.0
//* @created        2018-08-01
//* @systemLayer    Controller
//* ──────────────────────────────────────────────────────────────────────────────────────────────────
//* @changes
//* v1.0            soul6reaver@gmail.com
//* 2018-08-01      Changes
//* 
//* ─────────────────────────────────────────────────────────────────────────────────────────────────┘
//*/
//package com.travel.agency.controllers;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.travel.agency.entities.Role;
//import com.travel.agency.entities.User;
//import com.travel.agency.services.RoleService;
//import com.travel.agency.services.UserService;
//import com.travel.agency.utils.BikeUtils;
//
//@Controller
//public class UsersController {
//	
//	@Autowired
//	BCryptPasswordEncoder bcEncoder;
//
//	@Autowired
//	UserService userService;
//
//	@Autowired
//	RoleService roleService;
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Lists all users on this page in table.
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   String   Returns HTML template which is processing here
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@RequestMapping("users")
//	public String users(Model model) {
//
//		List<User> user = userService.findAll();
//		model.addAttribute("users", user);
//
//		return "users/users";
//	}
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Opens one selected user to view its details.
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    id       Path variable id for selected user, type int
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   String   Returns HTML template which is processing here
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@RequestMapping("users/{id}")
//	public String user(@PathVariable("id") int id, Model model) {
//
//		User user = userService.readById(id);
//		model.addAttribute("user", user);
//
//		return "users/user-profile";
//	}
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Opens conformation dialog for deleting selected user.
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    request  Http request for getting params sent
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   String   Returns HTML template which is processing here
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@GetMapping("users/delete")
//	public String deleteDialog(HttpServletRequest request, Model model) {
//		User user = userService.readById(Integer.valueOf(request.getParameter("id")));
//		model.addAttribute("user", user);
//
//		return "users/user-dialog";
//	}
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Used for refreshing table for users on ajax call
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    request  Http request for getting params sent
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   String   Returns HTML template which is processing here
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@RequestMapping("users/refresh")
//	public String refresh(HttpServletRequest request, Model model) {
//		List<User> user = userService.findAll();
//		model.addAttribute("users", user);
//
//		return "users/users-table";
//	}
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Used for deleting user after conformation dialog
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    request  Http request for getting params sent
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   JSON     Returns JSON of user info that is deleted
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@PostMapping(path = "users/delete", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public Map<String, Object> delete(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException  {
//		User user = userService.readById(Integer.valueOf(request.getParameter("id")));
//		String [] rel = {"orders", "role", "wishlists"};
//		
//		userService.delete(user);
//		
//		return BikeUtils.convertToHashMap(user, rel);
//	}
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Used for opening form html for updating user
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    id       Path variable id for selected user, type int
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   String   Returns HTML template which is processing here
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@GetMapping(path = "users/update/{id}")
//	public String userForm(@PathVariable("id") int id, Model model) {
//		List<Role> r = roleService.findAll();
//		
//		User u = userService.readById(Integer.valueOf(id));
//
//		model.addAttribute("user", u);
//		model.addAttribute("roles", r);
//		model.addAttribute("path", "/users/update/" + id);
//		
//		return "users/user-form";
//	}
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Used for submiting form for user update
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    u  	   User that is getting updated, type Class
//	  * @param    db       Binding result used for validation on update
//	  * @param    id       Path variable id of the user that is being updated 
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   String   Returns HTML template which is processing here, or redirect in success case
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@PostMapping("users/update/{id}")
//	public String update(@Valid @ModelAttribute User u, BindingResult bd, @PathVariable("id") int id, Model model) {
//		
//		List<Role> roles = roleService.findAll();
//		
//		if (bd.hasErrors()) {
//			User usr = userService.readById(u.getId());
//			u.setDob(usr.getDob());
//			model.addAttribute("roles", roles);
//			model.addAttribute("user", u);
//			model.addAttribute("path", "/users/update/" + id);
//			return "users/user-form";
//		}
//		
//		u.setPassword(bcEncoder.encode(u.getPassword()));
//		u.setActive(1);
//
//		userService.update(u);
//		
//		model.addAttribute("roles", roles);
//		model.addAttribute("user", u);
//
//		return "redirect:/users/" + id;
//	}
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Used for Opening user form for create 
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   String   Returns HTML template which is processing here
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@GetMapping("users/create")
//	public String userCreate(Model model) {
//		User u = new User();
//		List<Role> roles = roleService.findAll();
//
//		model.addAttribute("roles", roles);
//		model.addAttribute("user", u);
//		model.addAttribute("path", "/users/create");
//
//		return "users/user-form";
//	}
//	
//	/**
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┐
//	  * Used for submiting form on user create
//	  * ────────────────────────────────────────────────────────────────────────────────────────────────
//	  * @param    u  	   User that is getting updated, type Class
//	  * @param    db       Binding result used for validation on update
//	  * @param    Model    Model in which data is passed for accessing in HTML
//	  * @return   String   Returns HTML template which is processing here, or redirect if successful
//	  * @author   KUNA    
//	  * ───────────────────────────────────────────────────────────────────────────────────────────────┘
//	  */
//	@PostMapping("users/create")
//	public String create(@Valid @ModelAttribute User u, BindingResult bd, Model model) {
//		
//		List<Role> roles = roleService.findAll();
//		
//		if (bd.hasErrors()) {
//			User usr = userService.readById(u.getId());
//			u.setDob(usr.getDob());
//			model.addAttribute("user", u);
//			model.addAttribute("roles", roles);
//			model.addAttribute("path", "/users/create");
//            return "users/user-form";
//        }
//
//		u.setPassword(bcEncoder.encode(u.getPassword()));
//		u.setActive(1);
//		
//		userService.save(u);
//		
//		model.addAttribute("user",u);
//		
//		return "redirect:/users";		
//	}
//}
