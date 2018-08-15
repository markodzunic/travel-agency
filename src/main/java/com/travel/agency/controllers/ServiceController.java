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

import com.travel.agency.entities.Room;
import com.travel.agency.entities.Service;

import com.travel.agency.services.RoomService;
import com.travel.agency.services.ServiceService;

import com.travel.agency.utils.BikeUtils;

@Controller
public class ServiceController {

	@Autowired
	RoomService roomService;
	
	@Autowired
	ServiceService serviceService;
	
	//list of services
	@GetMapping("rooms/services/{id}")
	public String services(Model model) {
		List<Service> service= serviceService.findAll();
		model.addAttribute("services",service);
		return "services/services";
				
	}
	
	
	//delete dialog for service

		@GetMapping("services/delete")
		public String deleteService(HttpServletRequest request, Model model) {
			Service city = serviceService.readById(Integer.valueOf(request.getParameter("id")));
			model.addAttribute("services", city);

			return "services/service-dialog";
		}

		@RequestMapping("services/refresh")
		public String refreshService(HttpServletRequest request, Model model) {
			List<Service> service= serviceService.findAll();
			
			model.addAttribute("services", service);

			return "services/services-table";
		}

		@PostMapping(path = "services/delete", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> deleteC(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
			Service service = serviceService.readById(Integer.valueOf(request.getParameter("id")));
			serviceService.delete(service);
			String [] rel = {"room","ordersAccommodations"};
		
					
			return BikeUtils.convertToHashMap(service,rel);
		}
		
		
		
		//create accommodation get mapping
		
		@GetMapping("services/create")
		public String serviceCreate(Model model) {
			Service service = new Service();
			List<Room> rooms = roomService.findAll();

			model.addAttribute("rooms",rooms);
			model.addAttribute("service", service);
			model.addAttribute("path", "/services/create");

			return "services/service-form";
		}
		
		
		@PostMapping("services/create")
		public String cCreate(@Valid @ModelAttribute Service c, BindingResult bd, Model model) {
			
			List<Room> rooms = roomService.findAll();
			
			if (bd.hasErrors()) {
				System.out.println("errors");
				model.addAttribute("service", c);
				model.addAttribute("rooms", rooms);
				model.addAttribute("path", "services/create");
	            return "services/service-form";
	        }
			
						
		
			model.addAttribute("service", c);
			serviceService.save(c);

			return "redirect:/rooms/services/"+c.getRoom().getId();
			
		
		}
		
		
		
		
		//update service get method
		
		@GetMapping("services/update/{id}")
		public String update(@PathVariable("id") int id,Model model) {
			
		List<Room> room = roomService.findAll();
		Service c = serviceService.readById(Integer.valueOf(id));
			
		model.addAttribute("service", c);
		model.addAttribute("path","/services/update/" + id);
		model.addAttribute("rooms", room);
		
			return "services/service-form";
					
		}
		
		
		//update service post method
		
		@PostMapping("services/update/{id}")
		public String updateS(@Valid @ModelAttribute Service c, BindingResult bd, Model model) {
			List<Room> rooms = roomService.findAll();
			
			if (bd.hasErrors()) {
				model.addAttribute("service", c);
				model.addAttribute("rooms",rooms);
				model.addAttribute("path", "/services/update/"+c.getId());
	            return "services/service-form";
	        }
			
			model.addAttribute("service", c);
			
			
			
			serviceService.update(c);
			
			return "redirect:/rooms/services/"+c.getRoom().getId();
			
			
		}
		
		
		
		
}
