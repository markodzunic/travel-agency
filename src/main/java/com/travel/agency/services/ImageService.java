package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Image;



public interface ImageService {

	List<Image> findAll();
	
	Image readById(Integer id);
	
	public boolean delete(Image obj);
	
	public Image save(Image obj);
	
	
	
	public Image findImageByField(String field);
}
