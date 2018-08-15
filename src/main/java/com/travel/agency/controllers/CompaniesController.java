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

import com.travel.agency.entities.Company;
import com.travel.agency.entities.Transport;
import com.travel.agency.services.CompanyService;
import com.travel.agency.utils.BikeUtils;

@Controller
public class CompaniesController {
	
	@Autowired
	CompanyService companyService;
	
	
	//list of comapnies

		@GetMapping("/companies")
		public String destinations(Model model) {
			
			List<Company> company = companyService.findAll();
			model.addAttribute("companies", company);
			
			return "companies/companies";
					
		}
		
		
		//delete dialog for companies

				@GetMapping("companies/delete")
				public String deleteDialog(HttpServletRequest request, Model model) {
					Company company = companyService.readById(Integer.valueOf(request.getParameter("id")));
					model.addAttribute("companies", company);

					return "companies/company-dialog";
				}

				@RequestMapping("companies/refresh")
				public String refresh(HttpServletRequest request, Model model) {
					List<Company> company = companyService.findAll();
					
					model.addAttribute("companies", company);

					return "companies/companies-table";
				}

				@PostMapping(path = "companies/delete", produces = MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public Map<String, Object> delete(HttpServletRequest request, Model model) throws IllegalArgumentException, IllegalAccessException {
					Company company = companyService.readById(Integer.valueOf(request.getParameter("id")));
					companyService.delete(company);
					String [] rel = {"accommodations"};

					return BikeUtils.convertToHashMap(company,rel);
				
				}
				
				
				//create company get method
				
				@GetMapping("companies/create")
				public String companyCreate(Model model) {
					Company u = new Company();
					

					model.addAttribute("company", u);
					model.addAttribute("path", "companies/create");

					return "companies/company-form";

				}
				
				
				//create company post method
				
				@PostMapping("companies/create")
				public String createTransport(@Valid @ModelAttribute Company u, BindingResult bd, Model model) {
					
					
					if (bd.hasErrors()) {
						model.addAttribute("company", u);
						model.addAttribute("path", "companies/create");
			            return "companies/company-form";
			        }
					
					
					companyService.save(u);
					model.addAttribute("company",u);
					
					return "redirect:/companies";
								
							
				}
				
				
				//update company get method
				
				@GetMapping("companies/update/{id}")
				public String updateCompany(@PathVariable("id") int id,Model model) {
					Company c = companyService.readById(Integer.valueOf(id));
					
					model.addAttribute("company", c);

					model.addAttribute("path","companies/update/" + id);
					
						return "companies/company-form";
					
				}
				
				
				//update company post method
				
				@PostMapping("companies/update/{id}")
				public String updateCompany(@Valid @ModelAttribute Company c, BindingResult bd, Model model) {
					
					if (bd.hasErrors()) {
						model.addAttribute("company", c);
						model.addAttribute("path", "companies/update/"+c.getId());
			            return "companies/company-form";
			        }
					
					model.addAttribute("company", c);
					
					companyService.update(c);
					
					return "redirect:/companies";
					
					
				}
				
				
		
		

}
