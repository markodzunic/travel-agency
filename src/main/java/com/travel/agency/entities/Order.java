package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllOrder",
            query   =   "SELECT * " +
                        "FROM orders",
                        resultClass=Order.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdOrder",
            query   =   "SELECT * " +
                        "FROM orders " +
                        "WHERE id = :id",
                        resultClass=Order.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldOrder",
    		query	=	"SELECT * "+
    					"FROM orders "+
    					"WHERE :nameColumn = :value",
    					resultClass=Order.class
	)
})
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="admin_id")
	private int adminId;

	@Column(name="current_payment")
	private double currentPayment;

	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
	private Date orderDate;

	private byte paid;

	@Column(name="total_price")
	private double totalPrice;

	//bi-directional many-to-one association to OrderApartment
	@OneToMany(mappedBy="order")
	private List<OrderApartment> orderApartments;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdminId() {
		return this.adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public double getCurrentPayment() {
		return this.currentPayment;
	}

	public void setCurrentPayment(double currentPayment) {
		this.currentPayment = currentPayment;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public byte getPaid() {
		return this.paid;
	}

	public void setPaid(byte paid) {
		this.paid = paid;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderApartment> getOrderApartments() {
		return this.orderApartments;
	}

	public void setOrderApartments(List<OrderApartment> orderApartments) {
		this.orderApartments = orderApartments;
	}

	public OrderApartment addOrderApartment(OrderApartment orderApartment) {
		getOrderApartments().add(orderApartment);
		orderApartment.setOrder(this);

		return orderApartment;
	}

	public OrderApartment removeOrderApartment(OrderApartment orderApartment) {
		getOrderApartments().remove(orderApartment);
		orderApartment.setOrder(null);

		return orderApartment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}