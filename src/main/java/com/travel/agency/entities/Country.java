package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllCountry",
            query   =   "SELECT * " +
                        "FROM countries",
                        resultClass=Country.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdCountry",
            query   =   "SELECT * " +
                        "FROM countries " +
                        "WHERE id = :id",
                        resultClass=Country.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldCountry",
    		query	=	"SELECT *"+
    					"FROM countries"+
    					"WHERE :nameColumn = :value",
    					resultClass=Country.class
	)
})
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country")
	private List<City> cities;

	public Country() {
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

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setCountry(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setCountry(null);

		return city;
	}

}