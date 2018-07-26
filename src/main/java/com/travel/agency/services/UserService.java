package com.travel.agency.services;

import com.travel.agency.entities.User;

public interface UserService {
	
	public User findByUsername(String username);
	
	public User findByName(String username, String password);
}
