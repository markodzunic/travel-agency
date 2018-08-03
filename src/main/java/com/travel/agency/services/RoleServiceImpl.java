package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Role;

@Repository
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private GenericDAO<Role> genericDAO;
	
	@Override
	public List<Role> findAll() {
		return genericDAO.findAll(Role.class);
	}

	@Override
	public Role readById(Integer id) {
		return genericDAO.readById(Role.class, "id", id);
	}

	@Override
	public boolean delete(Role obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Role save(Role obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Role update(Role obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Role findRoleByField(String field) {
		try {
			return (Role) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM roles WHERE field=:field", Role.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
}
