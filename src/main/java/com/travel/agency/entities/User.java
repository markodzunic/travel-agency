package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.travel.agency.annotation.UniqueUser;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedNativeQueries({
	@NamedNativeQuery(name = "getAllUser", query = "SELECT * " + "FROM users", resultClass = User.class),
	@NamedNativeQuery(name = "getAllByIdUser", query = "SELECT * " + "FROM users "
			+ "WHERE id = :id", resultClass = User.class),
	@NamedNativeQuery(name = "getAllByFieldUser", query = "SELECT * " + "FROM users "
			+ "WHERE :nameColumn = :value", resultClass = User.class) })

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private byte active;

	private String adress;

	private String avatar;

	private String city;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Email(message = "wrong email")
	@NotNull(message = "email can not be empty")
	@UniqueUser
	private String email;

	private String gender;

	@Column(name = "last_name")
	@NotNull(message = "Last name can not be empty")
	@Size(min = 3, max = 30)
	private String lastName;

	@Column(name="middle_name")
	private String middleName;

	@NotNull(message = "Name can not be empty")
	@Size(min = 2, max = 30)
	private String name;

	private String passport;

	@NotNull(message = "Password can not be empty")
	@Size(min = 6, max = 255)
	private String password;

	private String phone;

	@Column(name="social_security")
	private String socialSecurity;

	private String state;

	@NotNull(message = "Username can not be empty")
	@Size(min = 3, max = 50)
	@UniqueUser(message = "username already exists")
	private String username;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user1")
	private List<Order> orders1;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user2")
	private List<Order> orders2;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roles_id")
	private Role role;

	//bi-directional many-to-one association to Wishlist
	@OneToMany(mappedBy="user")
	private List<Wishlist> wishlists;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassport() {
		return this.passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSocialSecurity() {
		return this.socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Order> getOrders1() {
		return this.orders1;
	}

	public void setOrders1(List<Order> orders1) {
		this.orders1 = orders1;
	}

	public Order addOrders1(Order orders1) {
		getOrders1().add(orders1);
		orders1.setUser1(this);

		return orders1;
	}

	public Order removeOrders1(Order orders1) {
		getOrders1().remove(orders1);
		orders1.setUser1(null);

		return orders1;
	}

	public List<Order> getOrders2() {
		return this.orders2;
	}

	public void setOrders2(List<Order> orders2) {
		this.orders2 = orders2;
	}

	public Order addOrders2(Order orders2) {
		getOrders2().add(orders2);
		orders2.setUser2(this);

		return orders2;
	}

	public Order removeOrders2(Order orders2) {
		getOrders2().remove(orders2);
		orders2.setUser2(null);

		return orders2;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist addWishlist(Wishlist wishlist) {
		getWishlists().add(wishlist);
		wishlist.setUser(this);

		return wishlist;
	}

	public Wishlist removeWishlist(Wishlist wishlist) {
		getWishlists().remove(wishlist);
		wishlist.setUser(null);

		return wishlist;
	}

}