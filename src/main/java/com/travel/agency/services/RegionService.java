package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Region;

public interface RegionService {

List<Region> findAll();
	
	Region readById(Integer id);
	
	public boolean delete(Region obj);
	
	public Region save(Region obj);
	
	public Region update(Region obj);
	
	public Region findRegionByField(String field);
}
