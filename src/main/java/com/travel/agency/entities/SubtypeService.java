package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;


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

	@Column(name="type_id")
	private int typeId;

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

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}