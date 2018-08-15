package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Service;

@Repository
public class ServiceServiceImpl implements ServiceService{

	@Autowired
	private GenericDAO<Service> genericDAO;
	
	@Override
	public List<Service> findAll() {
		return genericDAO.findAll(Service.class);
	}

	@Override
	public Service readById(Integer id) {
		return genericDAO.readById(Service.class, "id", id);
	}

	@Override
	public boolean delete(Service obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Service save(Service obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Service update(Service obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Service findServiceByField(String field) {
		try {
			return (Service) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM services INNER JOIN types ON types.id=services.types_id WHERE field=:field", Service.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
}
