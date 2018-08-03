package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Country;

@Repository
public class CountryServiceImpl implements CountryService{

	@Autowired
	private GenericDAO<Country> genericDAO;
	
	@Override
	public List<Country> findAll() {
		return genericDAO.findAll(Country.class);
	}

	@Override
	public Country readById(Integer id) {
		return genericDAO.readById(Country.class, "id", id);
	}

	@Override
	public boolean delete(Country obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Country save(Country obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Country update(Country obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Country findCountryByField(String field) {
		try {
			return (Country) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM countries WHERE field=:field", Country.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}


}
