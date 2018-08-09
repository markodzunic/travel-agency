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

import com.travel.agency.entities.City;
import com.travel.agency.entities.Country;
import com.travel.agency.entities.Role;
import com.travel.agency.entities.User;
import com.travel.agency.services.CityService;
import com.travel.agency.services.CountryService;

@Controller
public class DestinationsController { 
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	CityService cityService;
	
	
	//list of countries
	
	@GetMapping("/destinations")
	public String destinations(Model model) {
		
		List<Country> country = countryService.findAll();
		model.addAttribute("countries", country);
		
		return "destinations/countries";
				
	}
	
	//list of cities 
	@GetMapping("/destinations/cities")
	public String cities(Model model) {
		List<City> city= cityService.findAll();
		model.addAttribute("cities",city);
		return "destinations/city-table";
				
	}
	
	
    //play button 
	
	@GetMapping("destinations/{id}")
	public String country(@PathVariable("id") int id, Model model) {
		
		List<City> city = cityService.findAll();  //napravi novi query za iscitavanje iz koje zemlje je grad/ ucitava sve gradove u svim zemljama!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		Country country = countryService.readById(id);
		model.addAttribute("countries", country);
		model.addAttribute("cities",city);

		return "destinations/cities";
	}
	
	
	
	
	
	//delete dialog for countries

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
	
	
	//create country get method
	
	@GetMapping("destinations/create")
	public String countryCreate(Model model) {
		Country u = new Country();
		

		model.addAttribute("country", u);
		model.addAttribute("path", "destinations/create");

		return "destinations/country-form";

	}
	
	
	//create country post method
	
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
	
	
	
	//delete dialog for cities

		@GetMapping("cities/delete")
		public String deleteCity(HttpServletRequest request, Model model) {
			City city = cityService.readById(Integer.valueOf(request.getParameter("id")));
			model.addAttribute("cities", city);

			return "destinations/city-dialog";
		}

		@RequestMapping("cities/refresh")
		public String refreshCity(HttpServletRequest request, Model model) {
			List<City> city = cityService.findAll();
			
			model.addAttribute("cities", city);

			return "destinations/cities-table";
		}

		@PostMapping(path = "cities/delete", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public String deleteC(HttpServletRequest request, Model model) {
			City city = cityService.readById(Integer.valueOf(request.getParameter("id")));
			cityService.delete(city);

			return city.getName();
		}
	
		
	
		
		//create city get mapping
		
		@GetMapping("cities/create")
		public String cityCreate(Model model) {
			City city = new City();
			List<Country> country = countryService.findAll();

			model.addAttribute("countries", country);
			model.addAttribute("city", city);
			model.addAttribute("path", "/cities/create");

			return "destinations/city-form";
		}
		
		
		@PostMapping("cities/create")
		public String cCreate(@Valid @ModelAttribute City c, BindingResult bd, Model model) {
			
			List<Country> countries = countryService.findAll();
			
			if (bd.hasErrors()) {
				System.out.println("errors");
				model.addAttribute("city", c);
				model.addAttribute("countries", countries);
				model.addAttribute("path", "cities/create");
	            return "cities/city-form";
	        }
						
			c.setCountry(countryService.readById(6));
			model.addAttribute("city", c);
			cityService.save(c);
	
			return "redirect:/destinations/"+c.getCountry().getId();
			
		
		}
		
		
		

		
		
		

	
	
	
	
	
	
	
	
	
	
	
	
	

}
