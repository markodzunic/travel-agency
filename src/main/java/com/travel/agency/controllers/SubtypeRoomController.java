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


import com.travel.agency.entities.SubtypeRoom;
import com.travel.agency.entities.Type;
import com.travel.agency.services.SubtypeRoomService;
import com.travel.agency.services.TypeService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class SubtypeRoomController {
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	SubtypeRoomService subtypeRoomService;
	
	//list of accomodations
	@GetMapping("/types/subtyperooms")
	public String subtypes(Model model) {
		List<SubtypeRoom> room= subtypeRoomService.findAll();
		model.addAttribute("subtyperooms",room);
		return "subtyperooms/subtyperoom-table";
				
	}
	
	//delete dialog for accomodation

	@GetMapping("subtyperooms/delete")
	public String deleteSubtype(HttpServletRequest request, Model model) {
		SubtypeRoom city = subtypeRoomService.readById(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("subtyperooms", city);

		return "subtyperooms/subtyperoom-dialog";
	}

	@RequestMapping("subtyperooms/refresh")
	public String refreshSubtype(HttpServletRequest request, Model model) {
		List<SubtypeRoom> room= subtypeRoomService.findAll();
		
		model.addAttribute("subtyperooms", room);

		return "subtyperooms/subtyperooms-table";
	}

	@PostMapping(path = "subtyperooms/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteC(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
		SubtypeRoom room = subtypeRoomService.readById(Integer.valueOf(request.getParameter("id")));
		subtypeRoomService.delete(room);
		String [] rel = {"type","apartments"};
	
				
		return BikeUtils.convertToHashMap(room,rel);
	}
	
	
	
	
	//create accomodation get mapping
	
	@GetMapping("subtyperooms/create")
	public String subtyperoomCreate(Model model) {
		SubtypeRoom room = new SubtypeRoom();
		List<Type> type = typeService.findAll();

		model.addAttribute("types",type);
		model.addAttribute("subtyperoom", room);
		model.addAttribute("path", "/subtyperooms/create");

		return "subtyperooms/subtyperoom-form";
	}
	
	
	@PostMapping("subtyperooms/create")
	public String cCreate(@Valid @ModelAttribute SubtypeRoom c, BindingResult bd, Model model) {
		
		List<Type> types = typeService.findAll();
		
		if (bd.hasErrors()) {
			System.out.println("errors");
			model.addAttribute("subtyperoom", c);
			model.addAttribute("types", types);
			model.addAttribute("path", "subtyperooms/create");
            return "subtyperooms/subtyperoom-form";
        }
		
					
	
		model.addAttribute("subtyperoom", c);
		subtypeRoomService.save(c);

		return "redirect:/types/"+c.getType().getId();
		
	
	}
	
	

}
