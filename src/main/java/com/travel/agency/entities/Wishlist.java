package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the wishlist database table.
 * 
 */
@Entity
@NamedQuery(name="Wishlist.findAll", query="SELECT w FROM Wishlist w")
public class Wishlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;

	//bi-directional many-to-one association to WishlistApartment
	@OneToMany(mappedBy="wishlist")
	private List<WishlistApartment> wishlistApartments;

	public Wishlist() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<WishlistApartment> getWishlistApartments() {
		return this.wishlistApartments;
	}

	public void setWishlistApartments(List<WishlistApartment> wishlistApartments) {
		this.wishlistApartments = wishlistApartments;
	}

	public WishlistApartment addWishlistApartment(WishlistApartment wishlistApartment) {
		getWishlistApartments().add(wishlistApartment);
		wishlistApartment.setWishlist(this);

		return wishlistApartment;
	}

	public WishlistApartment removeWishlistApartment(WishlistApartment wishlistApartment) {
		getWishlistApartments().remove(wishlistApartment);
		wishlistApartment.setWishlist(null);

		return wishlistApartment;
	}

}