package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the apartments database table.
 * 
 */
@Entity
@Table(name="apartments")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllApartment",
            query   =   "SELECT * " +
                        "FROM apartments",
                        resultClass=Apartment.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdApartment",
            query   =   "SELECT * " +
                        "FROM apartments " +
                        "WHERE id = :id",
                        resultClass=Apartment.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldApartment",
    		query	=	"SELECT *"+
    					"FROM apartments"+
    					"WHERE :nameColumn = :value",
    					resultClass=Apartment.class
	)
})
public class Apartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte available;

	@Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo;

	@Lob
	private String description;

	private String name;

	private double price;

	private String region;

	private String thumb;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="cities_id")
	private City city;

	//bi-directional many-to-one association to Type
	@ManyToOne
	private Type type;

	//bi-directional many-to-one association to SubtypeRoom
	@ManyToOne
	@JoinColumn(name="subtype_room_id")
	private SubtypeRoom subtypeRoom;

	//bi-directional many-to-one association to SubtypeService
	@ManyToOne
	@JoinColumn(name="subtype_service_id")
	private SubtypeService subtypeService;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="images_id")
	private Image image;

	//bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	//bi-directional many-to-one association to Transport
	@ManyToOne
	private Transport transport;

	//bi-directional many-to-one association to ImagesApartment
	@OneToMany(mappedBy="apartment")
	private List<ImagesApartment> imagesApartments;

	//bi-directional many-to-one association to OrderApartment
	@OneToMany(mappedBy="apartment")
	private List<OrderApartment> orderApartments;

	//bi-directional many-to-one association to WishlistApartment
	@OneToMany(mappedBy="apartment")
	private List<WishlistApartment> wishlistApartments;

	public Apartment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getAvailable() {
		return this.available;
	}

	public void setAvailable(byte available) {
		this.available = available;
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public SubtypeRoom getSubtypeRoom() {
		return this.subtypeRoom;
	}

	public void setSubtypeRoom(SubtypeRoom subtypeRoom) {
		this.subtypeRoom = subtypeRoom;
	}

	public SubtypeService getSubtypeService() {
		return this.subtypeService;
	}

	public void setSubtypeService(SubtypeService subtypeService) {
		this.subtypeService = subtypeService;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Transport getTransport() {
		return this.transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public List<ImagesApartment> getImagesApartments() {
		return this.imagesApartments;
	}

	public void setImagesApartments(List<ImagesApartment> imagesApartments) {
		this.imagesApartments = imagesApartments;
	}

	public ImagesApartment addImagesApartment(ImagesApartment imagesApartment) {
		getImagesApartments().add(imagesApartment);
		imagesApartment.setApartment(this);

		return imagesApartment;
	}

	public ImagesApartment removeImagesApartment(ImagesApartment imagesApartment) {
		getImagesApartments().remove(imagesApartment);
		imagesApartment.setApartment(null);

		return imagesApartment;
	}

	public List<OrderApartment> getOrderApartments() {
		return this.orderApartments;
	}

	public void setOrderApartments(List<OrderApartment> orderApartments) {
		this.orderApartments = orderApartments;
	}

	public OrderApartment addOrderApartment(OrderApartment orderApartment) {
		getOrderApartments().add(orderApartment);
		orderApartment.setApartment(this);

		return orderApartment;
	}

	public OrderApartment removeOrderApartment(OrderApartment orderApartment) {
		getOrderApartments().remove(orderApartment);
		orderApartment.setApartment(null);

		return orderApartment;
	}

	public List<WishlistApartment> getWishlistApartments() {
		return this.wishlistApartments;
	}

	public void setWishlistApartments(List<WishlistApartment> wishlistApartments) {
		this.wishlistApartments = wishlistApartments;
	}

	public WishlistApartment addWishlistApartment(WishlistApartment wishlistApartment) {
		getWishlistApartments().add(wishlistApartment);
		wishlistApartment.setApartment(this);

		return wishlistApartment;
	}

	public WishlistApartment removeWishlistApartment(WishlistApartment wishlistApartment) {
		getWishlistApartments().remove(wishlistApartment);
		wishlistApartment.setApartment(null);

		return wishlistApartment;
	}

}