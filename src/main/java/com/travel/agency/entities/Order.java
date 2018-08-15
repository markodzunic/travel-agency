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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_current")
	private Date dateCurrent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_last")
	private Date dateLast;

	private String discount;

	@Column(name="past_payments")
	private String pastPayments;

	@Column(name="payment_method")
	private String paymentMethod;

	@Column(name="total_price")
	private String totalPrice;

	//bi-directional many-to-one association to OrdersAccommodation
	@ManyToOne
	@JoinColumn(name="accommodations_id")
	private OrdersAccommodation ordersAccommodation;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="statuses_id")
	private Status status;

	//bi-directional many-to-one association to Transport
	@ManyToOne
	@JoinColumn(name="transports_id")
	private Transport transport;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="admins_id")
	private User user2;

	//bi-directional many-to-one association to OrdersAccommodation
	@OneToMany(mappedBy="order")
	private List<OrdersAccommodation> ordersAccommodations;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCurrent() {
		return this.dateCurrent;
	}

	public void setDateCurrent(Date dateCurrent) {
		this.dateCurrent = dateCurrent;
	}

	public Date getDateLast() {
		return this.dateLast;
	}

	public void setDateLast(Date dateLast) {
		this.dateLast = dateLast;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPastPayments() {
		return this.pastPayments;
	}

	public void setPastPayments(String pastPayments) {
		this.pastPayments = pastPayments;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrdersAccommodation getOrdersAccommodation() {
		return this.ordersAccommodation;
	}

	public void setOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		this.ordersAccommodation = ordersAccommodation;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Transport getTransport() {
		return this.transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public List<OrdersAccommodation> getOrdersAccommodations() {
		return this.ordersAccommodations;
	}

	public void setOrdersAccommodations(List<OrdersAccommodation> ordersAccommodations) {
		this.ordersAccommodations = ordersAccommodations;
	}

	public OrdersAccommodation addOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		getOrdersAccommodations().add(ordersAccommodation);
		ordersAccommodation.setOrder(this);

		return ordersAccommodation;
	}

	public OrdersAccommodation removeOrdersAccommodation(OrdersAccommodation ordersAccommodation) {
		getOrdersAccommodations().remove(ordersAccommodation);
		ordersAccommodation.setOrder(null);

		return ordersAccommodation;
	}

}