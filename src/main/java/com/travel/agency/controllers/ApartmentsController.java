package com.travel.agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.travel.agency.services.ApartmentService;

@Controller
public class ApartmentsController {
	
	@Autowired
	ApartmentService apartmentService;

}
