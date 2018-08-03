package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.SubtypeService;

public interface SubtypeServiceService {
	 List<SubtypeService> findAll();
		
		SubtypeService readById(Integer id);
		
		public boolean delete(SubtypeService obj);
		
		public SubtypeService save(SubtypeService obj);
		
		public SubtypeService update(SubtypeService obj);
		
		public SubtypeService findSubtypeServiceByField(String field);

}
