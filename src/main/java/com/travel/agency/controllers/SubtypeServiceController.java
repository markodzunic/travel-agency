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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.travel.agency.entities.SubtypeService;
import com.travel.agency.entities.Type;
import com.travel.agency.services.SubtypeServiceService;
import com.travel.agency.services.TypeService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class SubtypeServiceController {

	@Autowired
	TypeService typeService;
	
	@Autowired
	SubtypeServiceService subtypeServiceService;
	
	//list of services
	@GetMapping("/types/subtypeservices")
	public String subtypes(Model model) {
		List<SubtypeService> service= subtypeServiceService.findAll();
		model.addAttribute("subtypeservices",service);
		return "subtypeservices/subtypeservices";
				
	}
	
	
	//delete dialog for service

		@GetMapping("subtypeservices/delete")
		public String deleteSubtype(HttpServletRequest request, Model model) {
			SubtypeService city = subtypeServiceService.readById(Integer.valueOf(request.getParameter("id")));
			model.addAttribute("subtypeservices", city);

			return "subtypeservices/subtypeservice-dialog";
		}

		@RequestMapping("subtypeservices/refresh")
		public String refreshSubtype(HttpServletRequest request, Model model) {
			List<SubtypeService> service= subtypeServiceService.findAll();
			
			model.addAttribute("subtypeservices", service);

			return "subtypeservices/subtypeservices-table";
		}

		@PostMapping(path = "subtypeservices/delete", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> deleteC(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
			SubtypeService service = subtypeServiceService.readById(Integer.valueOf(request.getParameter("id")));
			subtypeServiceService.delete(service);
			String [] rel = {"type","apartments"};
		
					
			return BikeUtils.convertToHashMap(service,rel);
		}
		
		
		
		//create accommodation get mapping
		
		@GetMapping("subtypeservices/create")
		public String subtypeserviceCreate(Model model) {
			SubtypeService service = new SubtypeService();
			List<Type> type = typeService.findAll();

			model.addAttribute("types",type);
			model.addAttribute("subtypeservice", service);
			model.addAttribute("path", "/subtypeservices/create");

			return "subtypeservices/subtypeservice-form";
		}
		
		
		@PostMapping("subtypeservices/create")
		public String cCreate(@Valid @ModelAttribute SubtypeService c, BindingResult bd, Model model) {
			
			List<Type> types = typeService.findAll();
			
			if (bd.hasErrors()) {
				System.out.println("errors");
				model.addAttribute("subtypeservice", c);
				model.addAttribute("types", types);
				model.addAttribute("path", "subtypeservices/create");
	            return "subtypeservices/subtypeservice-form";
	        }
			
						
		
			model.addAttribute("subtypeservice", c);
			subtypeServiceService.save(c);

			return "redirect:/types/"+c.getType().getId();
			
		
		}
		
		
		
		
}
