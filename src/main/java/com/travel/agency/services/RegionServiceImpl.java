package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Region;

@Repository
public class RegionServiceImpl implements RegionService{
	

	@Autowired
	private GenericDAO<Region> genericDAO;
	
	@Override
	public List<Region> findAll() {
		return genericDAO.findAll(Region.class);
	}

	@Override
	public Region readById(Integer id) {
		return genericDAO.readById(Region.class, "id", id);
	}

	@Override
	public boolean delete(Region obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Region save(Region obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Region update(Region obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Region findRegionByField(String field) {
		try {
			return (Region) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM regions WHERE field=:field", Region.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
