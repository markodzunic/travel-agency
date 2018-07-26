package com.travel.agency.services;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.User;

@Repository
public class UserServiceImpl implements UserService {
	
	@Autowired
	private GenericDAO<User> genericDAO;
	
	@Override
	public User findByUsername(String username) {
		try {
			return (User) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM users WHERE username=:username", User.class)
				.setParameter("username", username).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
	
	public User findByName(String username, String password) {
		try {
			return (User) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM users WHERE username=:username AND password=:sifra", User.class)
				.setParameter("username", username).setParameter("sifra", password).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
