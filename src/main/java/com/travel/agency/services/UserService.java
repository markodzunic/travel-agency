package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.User;

public interface UserService {
	
	List<User> findAll();
	
	User readById(Integer id);
	
	public boolean delete(User obj);
	
	public User save(User obj);
	
	public User update(User obj);
	
	public User findByUsername(String username);
	
	public User findByUsernamePassword(String username, String password);

	public User findUserByEmail(String email);

	public User findUserByField(String field);
	
	public List<User> findAllByRole(String role);
}
