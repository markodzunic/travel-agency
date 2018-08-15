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
import com.travel.agency.entities.Room;
import com.travel.agency.services.AccommodationService;
import com.travel.agency.services.RoomService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class RoomController {
	
	@Autowired
	AccommodationService accomodationService;
	
	@Autowired
	RoomService roomService;
	
	//list of accomomdations
	@GetMapping("/rooms")
	public String subtypes(Model model) {
		List<Room> room= roomService.findAll();
		model.addAttribute("rooms",room);
		return "rooms/rooms";
				
	}
	
	//delete dialog for accommodation

	@GetMapping("rooms/delete")
	public String deleteRoom(HttpServletRequest request, Model model) {
		Room city = roomService.readById(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("rooms", city);

		return "rooms/room-dialog";
	}

	@RequestMapping("rooms/refresh")
	public String refresh(HttpServletRequest request, Model model) {
		List<Room> room= roomService.findAll();
		
		model.addAttribute("rooms", room);

		return "rooms/rooms-table";
	}

	@PostMapping(path = "rooms/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteC(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
		Room room = roomService.readById(Integer.valueOf(request.getParameter("id")));
		roomService.delete(room);
		String [] rel = {"accommodation","facilities","ordersAccommodations", "services"};
	
				
		return BikeUtils.convertToHashMap(room,rel);
	}
	
	
	
	
	//create accommodation get mapping
	
	@GetMapping("rooms/create")
	public String roomCreate(Model model) {
		Room room = new Room();
	
		List<Accommodation> accommodations = accomodationService.findAll();
		
		model.addAttribute("room", room);
		model.addAttribute("accommodations", accommodations);
		model.addAttribute("path", "/rooms/create");

		return "rooms/room-form";
	}
	
	
	@PostMapping("rooms/create")
	public String cCreate(@Valid @ModelAttribute Room c, BindingResult bd, Model model) {
		
	
		
		if (bd.hasErrors()) {
			List<Accommodation> accommodations = accomodationService.findAll();
			model.addAttribute("accommodations", accommodations);
			System.out.println("errors");
			model.addAttribute("room", c);
		
			model.addAttribute("path", "rooms/create");
            return "rooms/room-form";
        }
		
					
	
		model.addAttribute("room", c);
		roomService.save(c);

		return "redirect:/rooms";
		
	
	}
	
	
	//update accommodation get method
	
	@GetMapping("rooms/update/{id}")
	public String subtyperoomUpdate(@PathVariable("id") int id,Model model) {
		
	
	Room c = roomService.readById(Integer.valueOf(id));
	List<Accommodation> accommodations = accomodationService.findAll();
	
	model.addAttribute("accommodations", accommodations);
	model.addAttribute("room", c);
	model.addAttribute("path","/rooms/update/" + id);

	
		return "rooms/room-form";
				
	}
	
	
	//update room post method 
	
	@PostMapping("rooms/update/{id}")
	public String updateSubtype(@Valid @ModelAttribute Room c, BindingResult bd, Model model) {

		
		if (bd.hasErrors()) {
			model.addAttribute("room", c);
			
			List<Accommodation> accommodations = accomodationService.findAll();
			model.addAttribute("accommodations", accommodations);
			
			model.addAttribute("path", "/rooms/update/"+c.getId());
            return "rooms/room-form";
        }
		
		model.addAttribute("room", c);
		
		
		
		roomService.update(c);
		
		return "redirect:/rooms";
		
		
	}
	
	
	
	

}
