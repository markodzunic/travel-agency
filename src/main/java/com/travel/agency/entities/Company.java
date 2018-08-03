package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the companies database table.
 * 
 */
@Entity
@Table(name="companies")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllCompany",
            query   =   "SELECT * " +
                        "FROM companies",
                        resultClass=Company.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdCompany",
            query   =   "SELECT * " +
                        "FROM companies " +
                        "WHERE id = :id",
                        resultClass=Company.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldCompany",
    		query	=	"SELECT *"+
    					"FROM companies"+
    					"WHERE :nameColumn = :value",
    					resultClass=Company.class
	)
})
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private String email;

	private String name;

	private String phone;

	private String pib;

	private int profit;

	private String website;

	//bi-directional many-to-one association to Apartment
	@OneToMany(mappedBy="company")
	private List<Apartment> apartments;

	public Company() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPib() {
		return this.pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public int getProfit() {
		return this.profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Apartment> getApartments() {
		return this.apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Apartment addApartment(Apartment apartment) {
		getApartments().add(apartment);
		apartment.setCompany(this);

		return apartment;
	}

	public Apartment removeApartment(Apartment apartment) {
		getApartments().remove(apartment);
		apartment.setCompany(null);

		return apartment;
	}

}