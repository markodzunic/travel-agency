package com.travel.agency.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.travel.agency.dao.GenericDAO;
import com.travel.agency.entities.Wishlist;

@Repository
public class WishlistServiceImpl implements WishlistService{
	@Autowired
	private GenericDAO<Wishlist> genericDAO;
	
	@Override
	public List<Wishlist> findAll() {
		return genericDAO.findAll(Wishlist.class);
	}

	@Override
	public Wishlist readById(Integer id) {
		return genericDAO.readById(Wishlist.class, "id", id);
	}

	@Override
	public boolean delete(Wishlist obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Wishlist save(Wishlist obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Wishlist update(Wishlist obj) {
		return genericDAO.update(obj);
	}

	@Override
	public Wishlist findWishlistByField(String field) {
		try {
			return (Wishlist) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM wishlist INNER JOIN users ON users.id=wishlist.users_id WHERE field=:field", Wishlist.class)
				.setParameter("field", field).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}

}
