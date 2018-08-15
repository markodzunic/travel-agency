package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Accommodation;


@Repository
public class AccommodationServiceImpl implements AccommodationService{
	@Autowired
	private GenericDAO<Accommodation> genericDAO;
	
	@Override
	public List<Accommodation> findAll() {
		return genericDAO.findAll(Accommodation.class);
	}

	@Override
	public Accommodation readById(Integer id) {
		return genericDAO.readById(Accommodation.class, "id", id);
	}

	@Override
	public boolean delete(Accommodation obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Accommodation save(Accommodation obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Accommodation update(Accommodation obj) {
		return genericDAO.update(obj);
	}

	@Override                                                 //naci kako join-ovati vise tabela u query!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public Accommodation findAccommodationByField(String field) {
		try {
			return (Accommodation) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM accommodations INNER JOIN countries ON countries.id=accommodations.countries_id WHERE field=:field", Accommodation.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Accommodation> findAllByCountry(String countryId) {
		try {
			return genericDAO.getManager().createNativeQuery(
				"SELECT * FROM accommodations WHERE countries_id=:field", Accommodation.class)
				.setParameter("field", countryId).getResultList();
		} catch (NoResultException nre){
			return null;
		}
	}

}
