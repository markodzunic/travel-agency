package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="role")
	private List<User> users;

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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}