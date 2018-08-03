package com.travel.agency.services;

import java.util.List;

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
	public List<User> findAll() {
		return genericDAO.findAll(User.class);
	}

	@Override
	public User readById(Integer id) {
		return genericDAO.readById(User.class, "id", id);
	}

	@Override
	public boolean delete(User obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public User save(User obj) {
		return genericDAO.save(obj);
	}

	@Override
	public User update(User obj) {
		return genericDAO.update(obj);
	}

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
	
	@Override
	public User findByUsernamePassword(String username, String password) {
		try {
			return (User) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM users WHERE username=:username AND password=:sifra", User.class)
				.setParameter("username", username).setParameter("sifra", password).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
		
	
	@Override
	public User findUserByEmail(String email) {
		try {
			return (User) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM user WHERE email=:email", User.class)
				.setParameter("email", email).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
	
	
	@Override
	public User findUserByField(String field) {
		try {
			return (User) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM user WHERE field=:field", User.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> findAllByRole(String role) {
		try {
			return genericDAO.getManager().createNativeQuery(
				"SELECT * FROM user INNER JOIN roles ON roles.id=user.roles_id WHERE roles.system_name=:roles", User.class)
				.setParameter("roles", role).getResultList();
		} catch (NoResultException nre){
			return null;
		}
	}

	
	

	
	
}
