package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.SubtypeService;

@Repository
public class SubtypeServiceServiceImpl implements SubtypeServiceService{

	@Autowired
	private GenericDAO<SubtypeService> genericDAO;
	
	@Override
	public List<SubtypeService> findAll() {
		return genericDAO.findAll(SubtypeService.class);
	}

	@Override
	public SubtypeService readById(Integer id) {
		return genericDAO.readById(SubtypeService.class, "id", id);
	}

	@Override
	public boolean delete(SubtypeService obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public SubtypeService save(SubtypeService obj) {
		return genericDAO.save(obj);
	}

	@Override
	public SubtypeService update(SubtypeService obj) {
		return genericDAO.update(obj);
	}

	@Override
	public SubtypeService findSubtypeServiceByField(String field) {
		try {
			return (SubtypeService) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM subtype_services INNER JOIN types ON types.id=subtype_services.types_id WHERE field=:field", SubtypeService.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
}
