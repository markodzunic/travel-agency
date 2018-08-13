package com.travel.agency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.travel.agency.entities.Apartment;
import com.travel.agency.services.ApartmentService;


@Component
public class ApartmentConverter implements Converter<String, Apartment> {

@Autowired
private ApartmentService apartmentService;

	@Override
	public Apartment convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return apartmentService.readById(id);
	}
}