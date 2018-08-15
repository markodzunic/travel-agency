package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the services database table.
 * 
 */
@Entity
@Table(name="services")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllService",
            query   =   "SELECT * " +
                        "FROM services",
                        resultClass=Service.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdService",
            query   =   "SELECT * " +
                        "FROM services " +
                        "WHERE id = :id",
                        resultClass=Service.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldService",
    		query	=	"SELECT * "+
    					"FROM services "+
    					"WHERE :nameColumn = :value",
    					resultClass=Service.class
	)
})
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private String price;

	//bi-directional many-to-one association to OrdersAccommodation
	@OneToMany(mappedBy="service")
	private List<OrdersAccommodation> ordersAccommodations;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="rooms_id")
	private Room room;

	public Service() {
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

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<OrdersAccommodation> getOrdersAccommodations() {
		return this.ordersAccommodations;
	}

	public void setOrdersAccommodations(List<OrdersAccommodation> ordersAccommodations) {
		this.ordersAccommodations = ordersAccommodations;
	}

	public OrdersAccommodation addOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		getOrdersAccommodations().add(ordersAccommodation);
		ordersAccommodation.setService(this);

		return ordersAccommodation;
	}

	public OrdersAccommodation removeOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		getOrdersAccommodations().remove(ordersAccommodation);
		ordersAccommodation.setService(null);

		return ordersAccommodation;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}