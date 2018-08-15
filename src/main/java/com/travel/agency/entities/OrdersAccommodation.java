package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orders_accommodations database table.
 * 
 */
@Entity
@Table(name="orders_accommodations")
@NamedQuery(name="OrdersAccommodation.findAll", query="SELECT o FROM OrdersAccommodation o")
public class OrdersAccommodation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="ordersAccommodation")
	private List<Order> orders;

	//bi-directional many-to-one association to Accommodation
	@ManyToOne
	@JoinColumn(name="accommodations_id")
	private Accommodation accommodation;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="orders_id")
	private Order order;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="rooms_id")
	private Room room;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="services_id")
	private Service service;

	public OrdersAccommodation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrdersAccommodation(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrdersAccommodation(null);

		return order;
	}

	public Accommodation getAccommodation() {
		return this.accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}