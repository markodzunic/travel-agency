package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Company;

@Repository
public class CompanyServiceImpl implements CompanyService {
	

	@Autowired
	private GenericDAO<Company> genericDAO;
	
	@Override
	public List<Company> findAll() {
		return genericDAO.findAll(Company.class);
	}

	@Override
	public Company readById(Integer id) {
		return genericDAO.readById(Company.class, "id", id);
	}

	@Override
	public boolean delete(Company obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Company save(Company obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Company update(Company obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Company findCompanyByField(String field) {
		try {
			return (Company) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM companies WHERE field=:field", Company.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
