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
import com.travel.agency.entities.SubtypeRoom;
import com.travel.agency.entities.Type;
import com.travel.agency.services.SubtypeRoomService;
import com.travel.agency.services.TypeService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class TypeController {
	
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	SubtypeRoomService subtypeRoomService;
	
	
	@GetMapping("/types")
	public String destinations(Model model) {
		
		List<Type> type = typeService.findAll();
		model.addAttribute("types", type);
		
		return "types/types";
				
	}
	
	
	
	
	   //play button 
	
		@GetMapping("types/{id}")
		public String subtype(@PathVariable("id") int id, Model model) {
			
			List<SubtypeRoom> city = subtypeRoomService.findAll(); 
			
			Type country = typeService.readById(id);
			model.addAttribute("types", country);
			model.addAttribute("subtyperooms",city);

			return "subtyperooms/subtyperooms";
		}
		
	
	
	
	
	//delete dialog for types

	@GetMapping("types/delete")
	public String deleteDialog(HttpServletRequest request, Model model) {
		Type type = typeService.readById(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("types", type);

		return "types/type-dialog";
	}

	@RequestMapping("types/refresh")
	public String refresh(HttpServletRequest request, Model model) {
		List<Type> type = typeService.findAll();
		
		model.addAttribute("types", type);

		return "types/types-table";
	}

	@PostMapping(path = "types/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
		Type type = typeService.readById(Integer.valueOf(request.getParameter("id")));
		typeService.delete(type);
		String [] rel = {"subtyperoom", "subtypeservice"};

		return BikeUtils.convertToHashMap(type,rel);
	
	}
	
	
	
	
	//create type get method
	
	@GetMapping("types/create")
	public String typeCreate(Model model) {
		Type u = new Type();
		

		model.addAttribute("type", u);
		model.addAttribute("path", "types/create");

		return "types/type-form";

	}
	
	
	//create type post method
	
	@PostMapping("types/create")
	public String createType(@Valid @ModelAttribute Type u, BindingResult bd, Model model) {
		
		
		if (bd.hasErrors()) {
			model.addAttribute("type", u);
			model.addAttribute("path", "types/create");
            return "types/type-form";
        }
		
		u.setSystemName(u.getName().trim().toLowerCase().replaceAll(" ", "_"));
		typeService.save(u);
		model.addAttribute("type",u);
		
		return "redirect:/types";
					
				
	}
	
	

	//update type get method
	
	@GetMapping("types/update/{id}")
	public String typeUpdate(@PathVariable("id") int id,Model model) {
		
	Type c = typeService.readById(Integer.valueOf(id));
		
	model.addAttribute("type", c);

	model.addAttribute("path","types/update/" + id);
	
		return "types/type-form";
				
	}
	
	
	//update type post method
	
	@PostMapping("types/update/{id}")
	public String updateType(@Valid @ModelAttribute Type c, BindingResult bd, Model model) {
		
		if (bd.hasErrors()) {
			model.addAttribute("type", c);
			model.addAttribute("path", "types/update/"+c.getId());
            return "types/type-form";
        }
		
		model.addAttribute("type", c);
		
		typeService.update(c);
		
		return "redirect:/types";
		
		
	}
	
	
	

}
