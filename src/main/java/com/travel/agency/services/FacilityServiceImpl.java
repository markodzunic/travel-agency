package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Facility;
@Repository
public class FacilityServiceImpl implements FacilityService{

	@Autowired
	private GenericDAO<Facility> genericDAO;
	
	@Override
	public List<Facility> findAll() {
		return genericDAO.findAll(Facility.class);
	}

	@Override
	public Facility readById(Integer id) {
		return genericDAO.readById(Facility.class, "id", id);
	}

	@Override
	public boolean delete(Facility obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Facility save(Facility obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Facility update(Facility obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Facility findFacilityByField(String field) {
		try {
			return (Facility) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM facilities WHERE field=:field", Facility.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
}
