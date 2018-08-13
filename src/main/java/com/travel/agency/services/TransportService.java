package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Transport;



public interface TransportService {
	
	List<Transport> findAll();
	
	Transport readById(Integer id);
	
	public boolean delete(Transport obj);
	
	public Transport save(Transport obj);
	
	public Transport update(Transport obj);
	
	public Transport findTransportByField(String field);

}
