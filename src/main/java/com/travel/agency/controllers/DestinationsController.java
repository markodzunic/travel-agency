package com.travel.agency.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travel.agency.entities.Country;
import com.travel.agency.services.CountryService;

@Controller
public class DestinationsController { 
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/destinations")
	public String destinations(Model model) {
		
		List<Country> country = countryService.findAll();
		model.addAttribute("countries", country);
		
		return "destinations/countries";
				
	}
	
	

	@GetMapping("destinations/{id}")
	public String country(@PathVariable("id") int id, Model model) {

		Country country = countryService.readById(id);
		model.addAttribute("countries", country);

		return "destinations/cities";
	}
	


	@GetMapping("countries/delete")
	public String deleteDialog(HttpServletRequest request, Model model) {
		Country country = countryService.readById(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("countries", country);

		return "destinations/country-dialog";
	}

	@RequestMapping("countries/refresh")
	public String refresh(HttpServletRequest request, Model model) {
		List<Country> country = countryService.findAll();
		
		model.addAttribute("countries", country);

		return "destinations/countries-table";
	}

	@PostMapping(path = "countries/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(HttpServletRequest request, Model model) {
		Country country = countryService.readById(Integer.valueOf(request.getParameter("id")));
		countryService.delete(country);

		return country.getName();
	}
	
	
	@GetMapping("destinations/create")
	public String countryCreate(Model model) {
		Country u = new Country();
		

		model.addAttribute("country", u);
		model.addAttribute("path", "destinations/create");

		return "destinations/country-form";

	}
	
	@PostMapping("destinations/create")
	public String createCountry(@Valid @ModelAttribute Country u, BindingResult bd, Model model) {
		
		
		if (bd.hasErrors()) {
			model.addAttribute("country", u);
			model.addAttribute("path", "destinations/create");
            return "destinations/country-form";
        }
		
		
		countryService.save(u);
		model.addAttribute("country",u);
		
		return "redirect:/destinations";
					
				
	}
	
	
	
	
	
	
	
	
	
	
	

}
