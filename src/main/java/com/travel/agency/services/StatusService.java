package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Status;

public interface StatusService {

	List<Status> findAll();
	
	Status readById(Integer id);
	
	public boolean delete(Status obj);
	
	public Status save(Status obj);
	
	public Status update(Status obj);
	
	public Status findStatusByField(String field);
}
