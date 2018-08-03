package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Company;

public interface CompanyService {

	List<Company> findAll();
	
	Company readById(Integer id);
	
	public boolean delete(Company obj);
	
	public Company save(Company obj);
	
	public Company update(Company obj);
	
	public Company findCompanyByField(String field);
}
