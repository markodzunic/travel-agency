package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Type;

@Repository
public class TypeServiceImpl implements TypeService {


	@Autowired
	private GenericDAO<Type> genericDAO;
	
	@Override
	public List<Type> findAll() {
		return genericDAO.findAll(Type.class);
	}

	@Override
	public Type readById(Integer id) {
		return genericDAO.readById(Type.class, "id", id);
	}

	@Override
	public boolean delete(Type obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Type save(Type obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Type update(Type obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Type findTypeByField(String field) {
		try {
			return (Type) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM types WHERE field=:field", Type.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
}
