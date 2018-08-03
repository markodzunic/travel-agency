package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Type;

public interface TypeService {

	
	List<Type> findAll();
	
	Type readById(Integer id);
	
	public boolean delete(Type obj);
	
	public Type save(Type obj);
	
	public Type update(Type obj);
	
	public Type findTypeByField(String field);
}
