package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Service;

public interface ServiceService {
	 List<Service> findAll();
		
		Service readById(Integer id);
		
		public boolean delete(Service obj);
		
		public Service save(Service obj);
		
		public Service update(Service obj);
		
		public Service findServiceByField(String field);

}
