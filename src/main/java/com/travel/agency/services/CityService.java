package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.City;

public interface CityService {
	
    List<City> findAll();
	
	City readById(Integer id);
	
	public boolean delete(City obj);
	
	public City save(City obj);
	
	public City update(City obj);
	
	public City findCityByField(String field);
	
	public List<City> findAllByCountry(String countryId);
}
