package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wishlists_accommodations database table.
 * 
 */
@Entity
@Table(name="wishlists_accommodations")
@NamedQuery(name="WishlistsAccommodation.findAll", query="SELECT w FROM WishlistsAccommodation w")
public class WishlistsAccommodation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Accommodation
	@ManyToOne
	@JoinColumn(name="accommodations_id")
	private Accommodation accommodation;

	//bi-directional many-to-one association to Wishlist
	@ManyToOne
	@JoinColumn(name="wishlists_id")
	private Wishlist wishlist;

	public WishlistsAccommodation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Accommodation getAccommodation() {
		return this.accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public Wishlist getWishlist() {
		return this.wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

}