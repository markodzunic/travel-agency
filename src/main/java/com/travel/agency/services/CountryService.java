package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Country;


public interface CountryService {
	
	List<Country> findAll();
	
	Country readById(Integer id);
	
	public boolean delete(Country obj);
	
	public Country save(Country obj);
	
	public Country update(Country obj);
	
	public Country findCountryByField(String field);

}
