package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Accommodation;

public interface AccommodationService {

    List<Accommodation> findAll();
	
	Accommodation readById(Integer id);
	
	public boolean delete(Accommodation obj);
	
	public Accommodation save(Accommodation obj);
	
	public Accommodation update(Accommodation obj);
	
	public Accommodation findAccommodationByField(String field);
	
	public List<Accommodation> findAllByCountry(String countryId);
}
