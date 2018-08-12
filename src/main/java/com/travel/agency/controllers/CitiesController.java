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
import com.travel.agency.services.CityService;
import com.travel.agency.services.CountryService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class CitiesController {
	
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	CityService cityService;
	
	
	
	//list of cities 
	@GetMapping("/destinations/cities")
	public String cities(Model model) {
		List<City> city= cityService.findAll();
		model.addAttribute("cities",city);
		return "cities/city-table";
				
	}
	
	
	
	//delete dialog for cities

		@GetMapping("cities/delete")
		public String deleteCity(HttpServletRequest request, Model model) {
			City city = cityService.readById(Integer.valueOf(request.getParameter("id")));
			model.addAttribute("cities", city);

			return "cities/city-dialog";
		}

		@RequestMapping("cities/refresh")
		public String refreshCity(HttpServletRequest request, Model model) {
			List<City> city = cityService.findAll();
			
			model.addAttribute("cities", city);

			return "cities/cities-table";
		}

		@PostMapping(path = "cities/delete", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> deleteC(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
			City city = cityService.readById(Integer.valueOf(request.getParameter("id")));
			cityService.delete(city);
			String [] rel = {"country","apartments"};
		
					
			return BikeUtils.convertToHashMap(city,rel);
		}
	
		
	
		
		//create city get mapping
		
		@GetMapping("cities/create")
		public String cityCreate(Model model) {
			City city = new City();
			List<Country> country = countryService.findAll();

			model.addAttribute("countries", country);
			model.addAttribute("city", city);
			model.addAttribute("path", "/cities/create");

			return "cities/city-form";
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
						
			c.setCountry(countryService.readById(1));
			model.addAttribute("city", c);
			cityService.save(c);
	
			return "redirect:/destinations/"+c.getCountry().getId();
			
		
		}
		
		
		
		//update city get method
		
		@GetMapping("cities/update/{id}")
		public String cityUpdate(@PathVariable("id") int id,Model model) {
			
		List<Country> country = countryService.findAll();
		City c = cityService.readById(Integer.valueOf(id));
			
		model.addAttribute("city", c);
		model.addAttribute("path","/cities/update/" + id);
		model.addAttribute("countries", country);
		
			return "cities/city-form";
					
		}
		
		
		//pdate city post method
		
		@PostMapping("cities/update/{id}")
		public String updateCity(@Valid @ModelAttribute City c, BindingResult bd, Model model) {
			List<Country> country = countryService.findAll();
			
			if (bd.hasErrors()) {
				model.addAttribute("city", c);
				model.addAttribute("countries",country);
				model.addAttribute("path", "/cities/update/"+c.getId());
	            return "cities/city-form";
	        }
			
			model.addAttribute("city", c);
			
			c.setCountry(countryService.readById(1));
			
			cityService.update(c);
			
			return "redirect:/destinations/"+c.getCountry().getId();
			
			
		}

}
