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
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllType",
            query   =   "SELECT * " +
                        "FROM types",
                        resultClass=Type.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdType",
            query   =   "SELECT * " +
                        "FROM types " +
                        "WHERE id = :id",
                        resultClass=Type.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldType",
    		query	=	"SELECT * "+
    					"FROM types "+
    					"WHERE :nameColumn = :value",
    					resultClass=Type.class
	)
})
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@Column(name="system_name")
	private String systemName;

	//bi-directional many-to-one association to Accommodation
	@OneToMany(mappedBy="type")
	private List<Accommodation> accommodations;

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

	public List<Accommodation> getAccommodations() {
		return this.accommodations;
	}

	public void setAccommodations(List<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}

	public Accommodation addAccommodation(Accommodation accommodation) {
		getAccommodations().add(accommodation);
		accommodation.setType(this);

		return accommodation;
	}

	public Accommodation removeAccommodation(Accommodation accommodation) {
		getAccommodations().remove(accommodation);
		accommodation.setType(null);

		return accommodation;
	}

}