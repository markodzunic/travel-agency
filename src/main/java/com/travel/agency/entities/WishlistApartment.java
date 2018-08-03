
package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wishlist_apartment database table.
 * 
 */
@Entity
@Table(name="wishlist_apartment")
@NamedQuery(name="WishlistApartment.findAll", query="SELECT w FROM WishlistApartment w")
public class WishlistApartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Wishlist
	@ManyToOne
	private Wishlist wishlist;

	//bi-directional many-to-one association to Apartment
	@ManyToOne
	@JoinColumn(name="apartments_id")
	private Apartment apartment;

	public WishlistApartment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Wishlist getWishlist() {
		return this.wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public Apartment getApartment() {
		return this.apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

}