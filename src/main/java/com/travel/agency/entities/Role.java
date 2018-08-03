package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllRole",
            query   =   "SELECT * " +
                        "FROM roles",
                        resultClass=Role.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdRole",
            query   =   "SELECT * " +
                        "FROM roles " +
                        "WHERE id = :id",
                        resultClass=Role.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldRole",
    		query	=	"SELECT * "+
    					"FROM roles "+
    					"WHERE :nameColumn = :value",
    					resultClass=Role.class
	)
})
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@Column(name="system_name")
	private String systemName;

	public Role() {
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