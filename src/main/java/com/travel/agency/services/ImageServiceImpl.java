package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Image;

@Repository
public class ImageServiceImpl implements ImageService {

	@Autowired
	private GenericDAO<Image> genericDAO;
	
	@Override
	public List<Image> findAll() {
		return genericDAO.findAll(Image.class);
	}

	@Override
	public Image readById(Integer id) {
		return genericDAO.readById(Image.class, "id", id);
	}

	@Override
	public boolean delete(Image obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Image save(Image obj) {
		return genericDAO.save(obj);
	}


	@Override
	public Image findImageByField(String field) {
		try {
			return (Image) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM images WHERE field=:field", Image.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
