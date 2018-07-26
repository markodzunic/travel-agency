package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the order_apartment database table.
 * 
 */
@Entity
@Table(name="order_apartment")
@NamedQuery(name="OrderApartment.findAll", query="SELECT o FROM OrderApartment o")
public class OrderApartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="orders_id")
	private Order order;

	//bi-directional many-to-one association to Apartment
	@ManyToOne
	@JoinColumn(name="apartments_id")
	private Apartment apartment;

	public OrderApartment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Apartment getApartment() {
		return this.apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

}