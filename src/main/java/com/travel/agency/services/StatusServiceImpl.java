package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Status;

@Repository
public class StatusServiceImpl  implements StatusService{
	@Autowired
	private GenericDAO<Status> genericDAO;
	
	@Override
	public List<Status> findAll() {
		return genericDAO.findAll(Status.class);
	}

	@Override
	public Status readById(Integer id) {
		return genericDAO.readById(Status.class, "id", id);
	}

	@Override
	public boolean delete(Status obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Status save(Status obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Status update(Status obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Status findStatusByField(String field) {
		try {
			return (Status) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM statuses WHERE field=:field", Status.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
