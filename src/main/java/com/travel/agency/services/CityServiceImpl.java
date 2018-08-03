package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.City;


@Repository
public class CityServiceImpl implements CityService{

	@Autowired
	private GenericDAO<City> genericDAO;
	
	@Override
	public List<City> findAll() {
		return genericDAO.findAll(City.class);
	}

	@Override
	public City readById(Integer id) {
		return genericDAO.readById(City.class, "id", id);
	}

	@Override
	public boolean delete(City obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public City save(City obj) {
		return genericDAO.save(obj);
	}

	@Override
	public City update(City obj) {
		return genericDAO.update(obj);
	}

	@Override
	public City findCityByField(String field) {
		try {
			return (City) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM cities INNER JOIN countries ON countries.id=cities.countries_id WHERE field=:field", City.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
