package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Apartment;

public interface ApartmentService {

    List<Apartment> findAll();
	
	Apartment readById(Integer id);
	
	public boolean delete(Apartment obj);
	
	public Apartment save(Apartment obj);
	
	public Apartment update(Apartment obj);
	
	public Apartment findApartmentByField(String field);
	
	public List<Apartment> findAllByCountry(String countryId);
}
