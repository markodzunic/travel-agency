package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subtype_rooms database table.
 * 
 */
@Entity
@Table(name="subtype_rooms")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllSubtypeRoom",
            query   =   "SELECT * " +
                        "FROM subtype_rooms",
                        resultClass=SubtypeRoom.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdSubtypeRoom",
            query   =   "SELECT * " +
                        "FROM subtype_rooms " +
                        "WHERE id = :id",
                        resultClass=SubtypeRoom.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldSubtypeRoom",
    		query	=	"SELECT * "+
    					"FROM subtype_rooms "+
    					"WHERE :nameColumn = :value",
    					resultClass=SubtypeRoom.class
	)
})
public class SubtypeRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Apartment
	@OneToMany(mappedBy="subtypeRoom")
	private List<Apartment> apartments;

	//bi-directional many-to-one association to Type
	@ManyToOne
	private Type type;

	public SubtypeRoom() {
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
		apartment.setSubtypeRoom(this);

		return apartment;
	}

	public Apartment removeApartment(Apartment apartment) {
		getApartments().remove(apartment);
		apartment.setSubtypeRoom(null);

		return apartment;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}