package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Facility;

public interface FacilityService {

	List<Facility> findAll();
	
	Facility readById(Integer id);
	
	public boolean delete(Facility obj);
	
	public Facility save(Facility obj);
	
	public Facility update(Facility obj);
	
	public Facility findFacilityByField(String field);
}
