package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the transports database table.
 * 
 */
@Entity
@Table(name="transports")
@NamedQuery(name="Transport.findAll", query="SELECT t FROM Transport t")
public class Transport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Apartment
	@OneToMany(mappedBy="transport")
	private List<Apartment> apartments;

	public Transport() {
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

	public List<Apartment> getApartments() {
		return this.apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Apartment addApartment(Apartment apartment) {
		getApartments().add(apartment);
		apartment.setTransport(this);

		return apartment;
	}

	public Apartment removeApartment(Apartment apartment) {
		getApartments().remove(apartment);
		apartment.setTransport(null);

		return apartment;
	}

}