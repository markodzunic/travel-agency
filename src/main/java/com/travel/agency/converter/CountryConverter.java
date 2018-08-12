package com.travel.agency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.travel.agency.entities.Country;
import com.travel.agency.services.CountryService;


@Component
public class CountryConverter implements Converter<String, Country> {

@Autowired
private CountryService countryService;

	@Override
	public Country convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return countryService.readById(id);
	}
}