package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the types database table.
 * 
 */
@Entity
@Table(name="types")
@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@Column(name="system_name")
	private String systemName;

	//bi-directional many-to-one association to Apartment
	@OneToMany(mappedBy="type")
	private List<Apartment> apartments;

	//bi-directional many-to-one association to SubtypeRoom
	@OneToMany(mappedBy="type")
	private List<SubtypeRoom> subtypeRooms;

	//bi-directional many-to-one association to SubtypeService
	@OneToMany(mappedBy="type")
	private List<SubtypeService> subtypeServices;

	public Type() {
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

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<Apartment> getApartments() {
		return this.apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Apartment addApartment(Apartment apartment) {
		getApartments().add(apartment);
		apartment.setType(this);

		return apartment;
	}

	public Apartment removeApartment(Apartment apartment) {
		getApartments().remove(apartment);
		apartment.setType(null);

		return apartment;
	}

	public List<SubtypeRoom> getSubtypeRooms() {
		return this.subtypeRooms;
	}

	public void setSubtypeRooms(List<SubtypeRoom> subtypeRooms) {
		this.subtypeRooms = subtypeRooms;
	}

	public SubtypeRoom addSubtypeRoom(SubtypeRoom subtypeRoom) {
		getSubtypeRooms().add(subtypeRoom);
		subtypeRoom.setType(this);

		return subtypeRoom;
	}

	public SubtypeRoom removeSubtypeRoom(SubtypeRoom subtypeRoom) {
		getSubtypeRooms().remove(subtypeRoom);
		subtypeRoom.setType(null);

		return subtypeRoom;
	}

	public List<SubtypeService> getSubtypeServices() {
		return this.subtypeServices;
	}

	public void setSubtypeServices(List<SubtypeService> subtypeServices) {
		this.subtypeServices = subtypeServices;
	}

	public SubtypeService addSubtypeService(SubtypeService subtypeService) {
		getSubtypeServices().add(subtypeService);
		subtypeService.setType(this);

		return subtypeService;
	}

	public SubtypeService removeSubtypeService(SubtypeService subtypeService) {
		getSubtypeServices().remove(subtypeService);
		subtypeService.setType(null);

		return subtypeService;
	}

}