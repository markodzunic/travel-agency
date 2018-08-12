package com.travel.agency.controllers;

import java.util.List;
import java.util.Map;

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

import com.travel.agency.entities.City;
import com.travel.agency.entities.Country;
import com.travel.agency.entities.Role;
import com.travel.agency.entities.User;
import com.travel.agency.services.CityService;
import com.travel.agency.services.CountryService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class CountriesController { 
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	CityService cityService;
	
	
	//list of countries
	
	@GetMapping("/destinations")
	public String destinations(Model model) {
		
		List<Country> country = countryService.findAll();
		model.addAttribute("countries", country);
		
		return "countries/countries";
				
	}

	
	
    //play button 
	
	@GetMapping("destinations/{id}")
	public String country(@PathVariable("id") int id, Model model) {
		
		List<City> city = cityService.findAll();  //napravi novi query za iscitavanje iz koje zemlje je grad/ ucitava sve gradove u svim zemljama!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		Country country = countryService.readById(id);
		model.addAttribute("countries", country);
		model.addAttribute("cities",city);

		return "cities/cities";
	}
	
	
	
	
	
	//delete dialog for countries

	@GetMapping("countries/delete")
	public String deleteDialog(HttpServletRequest request, Model model) {
		Country country = countryService.readById(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("countries", country);

		return "countries/country-dialog";
	}

	@RequestMapping("countries/refresh")
	public String refresh(HttpServletRequest request, Model model) {
		List<Country> country = countryService.findAll();
		
		model.addAttribute("countries", country);

		return "countries/countries-table";
	}

	@PostMapping(path = "countries/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
		Country country = countryService.readById(Integer.valueOf(request.getParameter("id")));
		countryService.delete(country);

		return BikeUtils.convertToHashMap(country,null);
	
	}
	
	
	//create country get method
	
	@GetMapping("destinations/create")
	public String countryCreate(Model model) {
		Country u = new Country();
		

		model.addAttribute("country", u);
		model.addAttribute("path", "destinations/create");

		return "countries/country-form";

	}
	
	
	//create country post method
	
	@PostMapping("destinations/create")
	public String createCountry(@Valid @ModelAttribute Country u, BindingResult bd, Model model) {
		
		
		if (bd.hasErrors()) {
			model.addAttribute("country", u);
			model.addAttribute("path", "destinations/create");
            return "countries/country-form";
        }
		
		
		countryService.save(u);
		model.addAttribute("country",u);
		
		return "redirect:/destinations";
					
				
	}
	
	

		
		
		

		
		
		

	
	
	
	
	
	
	
	
	
	
	
	
	

}
