package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Role;



public interface RoleService {
	

	List<Role> findAll();
	
	Role readById(Integer id);
	
	public boolean delete(Role obj);
	
	public Role save(Role obj);
	
	public Role update(Role obj);
	
	public Role findRoleByField(String field);

}
