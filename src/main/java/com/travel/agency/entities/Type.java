package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;


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
    		query	=	"SELECT *"+
    					"FROM types"+
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

}