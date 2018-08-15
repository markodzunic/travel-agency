package com.travel.agency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.travel.agency.entities.City;
import com.travel.agency.services.CityService;

@Component
public class CityConverter implements Converter<String, City> {

@Autowired
private CityService cityService;

	@Override
	public City convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return cityService.readById(id);
	}
}