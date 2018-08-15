package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name="regions")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllRegion",
            query   =   "SELECT * " +
                        "FROM regions",
                        resultClass=Region.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdRegion",
            query   =   "SELECT * " +
                        "FROM regions " +
                        "WHERE id = :id",
                        resultClass=Region.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldRegion",
    		query	=	"SELECT *"+
    					"FROM regions"+
    					"WHERE :nameColumn = :value",
    					resultClass=Region.class
	)
})
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@Column(name="system_name")
	private String systemName;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="region")
	private List<City> cities;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="countries_id")
	private Country country;

	public Region() {
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

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setRegion(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setRegion(null);

		return city;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}