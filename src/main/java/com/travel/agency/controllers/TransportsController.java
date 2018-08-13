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

import com.travel.agency.entities.Country;
import com.travel.agency.entities.Transport;
import com.travel.agency.services.TransportService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class TransportsController {
	
	
	@Autowired
	TransportService transportService;
	
	//list of transports

	@GetMapping("/transports")
	public String destinations(Model model) {
		
		List<Transport> transport = transportService.findAll();
		model.addAttribute("transports", transport);
		
		return "transports/transports";
				
	}
	
	
	//delete dialog for transports

		@GetMapping("transports/delete")
		public String deleteDialog(HttpServletRequest request, Model model) {
			Transport transport = transportService.readById(Integer.valueOf(request.getParameter("id")));
			model.addAttribute("transports", transport);

			return "transports/transport-dialog";
		}

		@RequestMapping("transports/refresh")
		public String refresh(HttpServletRequest request, Model model) {
			List<Transport> transport = transportService.findAll();
			
			model.addAttribute("transports", transport);

			return "transports/transports-table";
		}

		@PostMapping(path = "transports/delete", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> delete(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
			Transport transport = transportService.readById(Integer.valueOf(request.getParameter("id")));
			transportService.delete(transport);
			String [] rel = {"apartments"};

			return BikeUtils.convertToHashMap(transport,rel);
		
		}
		
		
		
		//create transport get method
		
		@GetMapping("transports/create")
		public String transportCreate(Model model) {
			Transport u = new Transport();
			

			model.addAttribute("transport", u);
			model.addAttribute("path", "transports/create");

			return "transports/transport-form";

		}
		
		
		//create transport post method
		
		@PostMapping("transports/create")
		public String createTransport(@Valid @ModelAttribute Transport u, BindingResult bd, Model model) {
			
			
			if (bd.hasErrors()) {
				model.addAttribute("transport", u);
				model.addAttribute("path", "transports/create");
	            return "transports/transport-form";
	        }
			
			
			transportService.save(u);
			model.addAttribute("transport",u);
			
			return "redirect:/transports";
						
					
		}
		
		//update transport get method
		
		@GetMapping("transports/update/{id}")
		public String updateTransport(@PathVariable("id") int id,Model model) {
			Transport c = transportService.readById(Integer.valueOf(id));
			
			model.addAttribute("transport", c);

			model.addAttribute("path","transports/update/" + id);
			
				return "transports/transport-form";
			
		}
		
		
		//update transport post method
		
		@PostMapping("transports/update/{id}")
		public String updateCountry(@Valid @ModelAttribute Transport c, BindingResult bd, Model model) {
			
			if (bd.hasErrors()) {
				model.addAttribute("transport", c);
				model.addAttribute("path", "transports/update/"+c.getId());
	            return "transports/transport-form";
	        }
			
			model.addAttribute("transport", c);
			
			transportService.update(c);
			
			return "redirect:/transports";
			
			
		}
		
		
		
	
	
	

}
