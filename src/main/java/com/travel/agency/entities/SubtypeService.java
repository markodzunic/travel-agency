package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subtype_services database table.
 * 
 */
@Entity
@Table(name="subtype_services")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllSubtypeService",
            query   =   "SELECT * " +
                        "FROM subtype_services",
                        resultClass=SubtypeService.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdSubtypeService",
            query   =   "SELECT * " +
                        "FROM subtype_services " +
                        "WHERE id = :id",
                        resultClass=SubtypeService.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldSubtypeService",
    		query	=	"SELECT * "+
    					"FROM subtype_services "+
    					"WHERE :nameColumn = :value",
    					resultClass=SubtypeService.class
	)
})
public class SubtypeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Apartment
	@OneToMany(mappedBy="subtypeService")
	private List<Apartment> apartments;

	//bi-directional many-to-one association to Type
	@ManyToOne
	private Type type;

	public SubtypeService() {
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
		apartment.setSubtypeService(this);

		return apartment;
	}

	public Apartment removeApartment(Apartment apartment) {
		getApartments().remove(apartment);
		apartment.setSubtypeService(null);

		return apartment;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}