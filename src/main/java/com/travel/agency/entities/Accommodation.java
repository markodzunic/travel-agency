package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the accommodations database table.
 * 
 */
@Entity
@Table(name="accommodations")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllAccommodation",
            query   =   "SELECT * " +
                        "FROM accommodations",
                        resultClass=Accommodation.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdAccommodation",
            query   =   "SELECT * " +
                        "FROM accommodations " +
                        "WHERE id = :id",
                        resultClass=Accommodation.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldAccommodation",
    		query	=	"SELECT *"+
    					"FROM accommodations "+
    					"WHERE :nameColumn = :value",
    					resultClass=Accommodation.class
	)
})
public class Accommodation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String descrtiption;

	@Column(name="is_available")
	private byte isAvailable;

	private String name;

	private String thumb;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="cities_id")
	private City city;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="companies_id")
	private Company company;

	//bi-directional many-to-one association to ImagesAccommodation
	@ManyToOne
	@JoinColumn(name="images_id")
	private ImagesAccommodation imagesAccommodation;

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="types_id")
	private Type type;

	//bi-directional many-to-one association to ImagesAccommodation
	@OneToMany(mappedBy="accommodation")
	private List<ImagesAccommodation> imagesAccommodations;

	//bi-directional many-to-one association to OrdersAccommodation
	@OneToMany(mappedBy="accommodation")
	private List<OrdersAccommodation> ordersAccommodations;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="accommodation")
	private List<Room> rooms;

	//bi-directional many-to-one association to WishlistsAccommodation
	@OneToMany(mappedBy="accommodation")
	private List<WishlistsAccommodation> wishlistsAccommodations;

	public Accommodation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrtiption() {
		return this.descrtiption;
	}

	public void setDescrtiption(String descrtiption) {
		this.descrtiption = descrtiption;
	}

	public byte getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(byte isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumb() {
		return this.thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ImagesAccommodation getImagesAccommodation() {
		return this.imagesAccommodation;
	}

	public void setImagesAccommodation(ImagesAccommodation imagesAccommodation) {
		this.imagesAccommodation = imagesAccommodation;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<ImagesAccommodation> getImagesAccommodations() {
		return this.imagesAccommodations;
	}

	public void setImagesAccommodations(List<ImagesAccommodation> imagesAccommodations) {
		this.imagesAccommodations = imagesAccommodations;
	}

	public ImagesAccommodation addImagesAccommodation(ImagesAccommodation imagesAccommodation) {
		getImagesAccommodations().add(imagesAccommodation);
		imagesAccommodation.setAccommodation(this);

		return imagesAccommodation;
	}

	public ImagesAccommodation removeImagesAccommodation(ImagesAccommodation imagesAccommodation) {
		getImagesAccommodations().remove(imagesAccommodation);
		imagesAccommodation.setAccommodation(null);

		return imagesAccommodation;
	}

	public List<OrdersAccommodation> getOrdersAccommodations() {
		return this.ordersAccommodations;
	}

	public void setOrdersAccommodations(List<OrdersAccommodation> ordersAccommodations) {
		this.ordersAccommodations = ordersAccommodations;
	}

	public OrdersAccommodation addOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		getOrdersAccommodations().add(ordersAccommodation);
		ordersAccommodation.setAccommodation(this);

		return ordersAccommodation;
	}

	public OrdersAccommodation removeOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		getOrdersAccommodations().remove(ordersAccommodation);
		ordersAccommodation.setAccommodation(null);

		return ordersAccommodation;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(Room room) {
		getRooms().add(room);
		room.setAccommodation(this);

		return room;
	}

	public Room removeRoom(Room room) {
		getRooms().remove(room);
		room.setAccommodation(null);

		return room;
	}

	public List<WishlistsAccommodation> getWishlistsAccommodations() {
		return this.wishlistsAccommodations;
	}

	public void setWishlistsAccommodations(List<WishlistsAccommodation> wishlistsAccommodations) {
		this.wishlistsAccommodations = wishlistsAccommodations;
	}

	public WishlistsAccommodation addWishlistsAccommodation(WishlistsAccommodation wishlistsAccommodation) {
		getWishlistsAccommodations().add(wishlistsAccommodation);
		wishlistsAccommodation.setAccommodation(this);

		return wishlistsAccommodation;
	}

	public WishlistsAccommodation removeWishlistsAccommodation(WishlistsAccommodation wishlistsAccommodation) {
		getWishlistsAccommodations().remove(wishlistsAccommodation);
		wishlistsAccommodation.setAccommodation(null);

		return wishlistsAccommodation;
	}

}