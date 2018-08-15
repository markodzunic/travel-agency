package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cities database table.
 * 
 */
@Entity
@Table(name="cities")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllCity",
            query   =   "SELECT * " +
                        "FROM cities",
                        resultClass=City.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdCity",
            query   =   "SELECT * " +
                        "FROM cities " +
                        "WHERE id = :id",
                        resultClass=City.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldCity",
    		query	=	"SELECT *"+
    					"FROM cities "+
    					"WHERE :nameColumn = :value",
    					resultClass=City.class
	)
})
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@Column(name="system_name")
	private String systemName;

	//bi-directional many-to-one association to Accommodation
	@OneToMany(mappedBy="city")
	private List<Accommodation> accommodations;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="countries_id")
	private Country country;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="regions_id")
	private Region region;

	public City() {
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
		accommodation.setCity(this);

		return accommodation;
	}

	public Accommodation removeAccommodation(Accommodation accommodation) {
		getAccommodations().remove(accommodation);
		accommodation.setCity(null);

		return accommodation;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}