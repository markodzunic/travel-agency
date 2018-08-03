package com.travel.agency.services;

import java.util.List;

import com.travel.agency.entities.Wishlist;

public interface WishlistService {
    List<Wishlist> findAll();
	
	Wishlist readById(Integer id);
	
	public boolean delete(Wishlist obj);
	
	public Wishlist save(Wishlist obj);
	
	public Wishlist update(Wishlist obj);
	
	public Wishlist findWishlistByField(String field);

}
