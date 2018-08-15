package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the facilities database table.
 * 
 */
@Entity
@Table(name="facilities")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllFacility",
            query   =   "SELECT * " +
                        "FROM facilities",
                        resultClass=Facility.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdFacility",
            query   =   "SELECT * " +
                        "FROM facilities " +
                        "WHERE id = :id",
                        resultClass=Facility.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldFacility",
    		query	=	"SELECT *"+
    					"FROM facilities"+
    					"WHERE :nameColumn = :value",
    					resultClass=Facility.class
	)
})
public class Facility implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String icon;

	private String name;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="rooms_id")
	private Room room;

	public Facility() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}