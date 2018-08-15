package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the wishlists database table.
 * 
 */
@Entity
@Table(name="wishlists")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllWishlist",
            query   =   "SELECT * " +
                        "FROM wishlists",
                        resultClass=Wishlist.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdWishlist",
            query   =   "SELECT * " +
                        "FROM wishlists " +
                        "WHERE id = :id",
                        resultClass=Wishlist.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldWishlist",
    		query	=	"SELECT * "+
    					"FROM wishlists "+
    					"WHERE :nameColumn = :value",
    					resultClass=Wishlist.class
	)
})
public class Wishlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;

	//bi-directional many-to-one association to WishlistsAccommodation
	@OneToMany(mappedBy="wishlist")
	private List<WishlistsAccommodation> wishlistsAccommodations;

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

	public List<WishlistsAccommodation> getWishlistsAccommodations() {
		return this.wishlistsAccommodations;
	}

	public void setWishlistsAccommodations(List<WishlistsAccommodation> wishlistsAccommodations) {
		this.wishlistsAccommodations = wishlistsAccommodations;
	}

	public WishlistsAccommodation addWishlistsAccommodation(WishlistsAccommodation wishlistsAccommodation) {
		getWishlistsAccommodations().add(wishlistsAccommodation);
		wishlistsAccommodation.setWishlist(this);

		return wishlistsAccommodation;
	}

	public WishlistsAccommodation removeWishlistsAccommodation(WishlistsAccommodation wishlistsAccommodation) {
		getWishlistsAccommodations().remove(wishlistsAccommodation);
		wishlistsAccommodation.setWishlist(null);

		return wishlistsAccommodation;
	}

}