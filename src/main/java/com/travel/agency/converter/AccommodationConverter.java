package com.travel.agency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.travel.agency.entities.Accommodation;
import com.travel.agency.services.AccommodationService;


@Component
public class AccommodationConverter implements Converter<String, Accommodation> {

@Autowired
private AccommodationService accommodationService;

	@Override
	public Accommodation convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return accommodationService.readById(id);
	}
}