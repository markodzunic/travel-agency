package com.travel.agency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.travel.agency.entities.Company;
import com.travel.agency.services.CompanyService;

@Component
public class CompanyConverter implements Converter<String, Company> {

@Autowired
private CompanyService companyService;

	@Override
	public Company convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return companyService.readById(id);
	}
}