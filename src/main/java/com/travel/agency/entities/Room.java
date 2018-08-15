package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rooms database table.
 * 
 */
@Entity
@Table(name="rooms")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllRoom",
            query   =   "SELECT * " +
                        "FROM rooms",
                        resultClass=Room.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdRoom",
            query   =   "SELECT * " +
                        "FROM rooms " +
                        "WHERE id = :id",
                        resultClass=Room.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldRoom",
    		query	=	"SELECT * "+
    					"FROM rooms "+
    					"WHERE :nameColumn = :value",
    					resultClass=Room.class
	)
})
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="is_available")
	private byte isAvailable;

	private String name;

	private String price;

	//bi-directional many-to-one association to Facility
	@OneToMany(mappedBy="room")
	private List<Facility> facilities;

	//bi-directional many-to-one association to OrdersAccommodation
	@OneToMany(mappedBy="room")
	private List<OrdersAccommodation> ordersAccommodations;

	//bi-directional many-to-one association to Accommodation
	@ManyToOne
	@JoinColumn(name="accommodations_id")
	private Accommodation accommodation;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="room")
	private List<Service> services;

	public Room() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<Facility> getFacilities() {
		return this.facilities;
	}

	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}

	public Facility addFacility(Facility facility) {
		getFacilities().add(facility);
		facility.setRoom(this);

		return facility;
	}

	public Facility removeFacility(Facility facility) {
		getFacilities().remove(facility);
		facility.setRoom(null);

		return facility;
	}

	public List<OrdersAccommodation> getOrdersAccommodations() {
		return this.ordersAccommodations;
	}

	public void setOrdersAccommodations(List<OrdersAccommodation> ordersAccommodations) {
		this.ordersAccommodations = ordersAccommodations;
	}

	public OrdersAccommodation addOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		getOrdersAccommodations().add(ordersAccommodation);
		ordersAccommodation.setRoom(this);

		return ordersAccommodation;
	}

	public OrdersAccommodation removeOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		getOrdersAccommodations().remove(ordersAccommodation);
		ordersAccommodation.setRoom(null);

		return ordersAccommodation;
	}

	public Accommodation getAccommodation() {
		return this.accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setRoom(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setRoom(null);

		return service;
	}

}