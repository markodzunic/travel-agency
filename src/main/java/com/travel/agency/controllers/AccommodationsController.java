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

import com.travel.agency.entities.Accommodation;
import com.travel.agency.entities.City;
import com.travel.agency.entities.Company;
import com.travel.agency.entities.Image;
import com.travel.agency.entities.Type;
import com.travel.agency.services.AccommodationService;
import com.travel.agency.services.CityService;
import com.travel.agency.services.CompanyService;
import com.travel.agency.services.TypeService;
import com.travel.agency.utils.BikeUtils;



@Controller
public class AccommodationsController {
	
	@Autowired
	AccommodationService accommodationService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CityService cityService;
	
	
	
	@GetMapping("/accommodations")
	public String subtypes(Model model) {
		List<Accommodation> acc= accommodationService.findAll();
		model.addAttribute("accommodations",acc);
		for (Accommodation a: acc ) {
			for (Image i : a.getImages()) {
				System.out.println(i.getName());
			}
		}
		return "accommodations/accommodations";
				
	}
	
	
	//delete dialog for accommodation

		@GetMapping("accommodations/delete")
		public String deleteRoom(HttpServletRequest request, Model model) {
			Accommodation acc = accommodationService.readById(Integer.valueOf(request.getParameter("id")));
			model.addAttribute("accommodations", acc);

			return "accommodations/accommodation-dialog";
		}

		@RequestMapping("accommodations/refresh")
		public String refresh(HttpServletRequest request, Model model) {
			List<Accommodation> acc= accommodationService.findAll();
			
			model.addAttribute("accommodations", acc);

			return "accommodations/accommodations-table";
		}

		@PostMapping(path = "accommodations/delete", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> deleteC(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
			Accommodation acc = accommodationService.readById(Integer.valueOf(request.getParameter("id")));
			accommodationService.delete(acc);
			String [] rel = {"city","company","imagesAccommodation", "type","imagesAccommodations","ordersAccommodations","rooms","wishlistsAccommodations"};
		
					
			return BikeUtils.convertToHashMap(acc,rel);
		}
		
		
		//create accommodation get mapping
		
		@GetMapping("accommodations/create")
		public String accCreate(Model model) {
			Accommodation acc = new Accommodation();
		
			List<Type> types = typeService.findAll();
			List<Company> companies = companyService.findAll();
			List<City> cities = cityService.findAll();
			
			model.addAttribute("types", types);
			model.addAttribute("companies",companies);
			model.addAttribute("cities",cities);
			model.addAttribute("accommodation", acc);
			model.addAttribute("path", "/accommodations/create");

			return "accommodations/accommodation-form";
		}
		
		
		
		@PostMapping("accommodations/create")
		public String cCreate(@Valid @ModelAttribute Accommodation c, BindingResult bd, Model model) {
			
		
			
			if (bd.hasErrors()) {
				List<Type> types = typeService.findAll();
				List<Company> companies = companyService.findAll();
				List<City> cities = cityService.findAll();
				model.addAttribute("types", types);
				model.addAttribute("companies",companies);
				model.addAttribute("cities",cities);
				System.out.println("errors");
				model.addAttribute("room", c);
			
				model.addAttribute("path", "accommodations/create");
	            return "accommodations/accommodation-form";
	        }
			
						
		
			
			accommodationService.save(c);

			return "redirect:/accommodations";
						
		}
		
		//update accommodation get method
		
		@GetMapping("accommodations/update/{id}")
		public String aUpdate(@PathVariable("id") int id,Model model) {
			
		
		Accommodation c = accommodationService.readById(Integer.valueOf(id));
		List<Type> types = typeService.findAll();
		List<Company> companies = companyService.findAll();
		List<City> cities = cityService.findAll();
		
		model.addAttribute("types", types);
		model.addAttribute("companies",companies);
		model.addAttribute("cities",cities);
		model.addAttribute("accommodation", c);
		model.addAttribute("path","/accommodations/update/" + id);

		
			return "accommodations/accommodation-form";
					
		}
		
		
		
		//update acccommodations post method 
		
		@PostMapping("accommodations/update/{id}")
		public String updateAccomodation(@Valid @ModelAttribute Accommodation c, BindingResult bd, Model model) {

			
			if (bd.hasErrors()) {
				model.addAttribute("accommodation", c);
				
				List<Type> types = typeService.findAll();
				List<Company> companies = companyService.findAll();
				List<City> cities = cityService.findAll();
				model.addAttribute("types", types);
				model.addAttribute("companies",companies);
				model.addAttribute("cities",cities);
				
				model.addAttribute("path", "/accommodations/update/"+c.getId());
	            return "accommodations/accommodation-form";
	        }
			
			
			model.addAttribute("accommodation", c);
			accommodationService.update(c);
			
			return "redirect:/accommodations";
			
			
		}
		
		
	
		
		

}
