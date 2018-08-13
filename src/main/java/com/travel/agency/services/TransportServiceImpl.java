package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Transport;

@Repository
public class TransportServiceImpl implements TransportService{
	@Autowired
	private GenericDAO<Transport> genericDAO;
	
	@Override
	public List<Transport> findAll() {
		return genericDAO.findAll(Transport.class);
	}

	@Override
	public Transport readById(Integer id) {
		return genericDAO.readById(Transport.class, "id", id);
	}

	@Override
	public boolean delete(Transport obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Transport save(Transport obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Transport update(Transport obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Transport findTransportByField(String field) {
		try {
			return (Transport) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM types WHERE field=:field", Transport.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
