package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Apartment;


@Repository
public class ApartmentServiceImpl implements ApartmentService{
	@Autowired
	private GenericDAO<Apartment> genericDAO;
	
	@Override
	public List<Apartment> findAll() {
		return genericDAO.findAll(Apartment.class);
	}

	@Override
	public Apartment readById(Integer id) {
		return genericDAO.readById(Apartment.class, "id", id);
	}

	@Override
	public boolean delete(Apartment obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Apartment save(Apartment obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Apartment update(Apartment obj) {
		return genericDAO.update(obj);
	}

	@Override                                                 //naci kako join-ovati vise tabela u query!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public Apartment findApartmentByField(String field) {
		try {
			return (Apartment) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM apartments INNER JOIN countries ON countries.id=apartments.countries_id WHERE field=:field", Apartment.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Apartment> findAllByCountry(String countryId) {
		try {
			return genericDAO.getManager().createNativeQuery(
				"SELECT * FROM apartments WHERE countries_id=:field", Apartment.class)
				.setParameter("field", countryId).getResultList();
		} catch (NoResultException nre){
			return null;
		}
	}

}
