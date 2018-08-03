package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the images database table.
 * 
 */
@Entity
@Table(name="images")
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllImage",
            query   =   "SELECT * " +
                        "FROM images",
                        resultClass=Image.class
    ),
    @NamedNativeQuery(
            name	=   "getAllByIdImage",
            query   =   "SELECT * " +
                        "FROM images " +
                        "WHERE id = :id",
                        resultClass=Image.class
    ),
    @NamedNativeQuery(
    		name	=	"getAllByFieldImage",
    		query	=	"SELECT * "+
    					"FROM images "+
    					"WHERE :nameColumn = :value",
    					resultClass=Image.class
	)
})
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private String path;

	//bi-directional many-to-one association to Apartment
	@OneToMany(mappedBy="image")
	private List<Apartment> apartments;

	//bi-directional many-to-one association to ImagesApartment
	@OneToMany(mappedBy="image")
	private List<ImagesApartment> imagesApartments;

	public Image() {
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

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Apartment> getApartments() {
		return this.apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Apartment addApartment(Apartment apartment) {
		getApartments().add(apartment);
		apartment.setImage(this);

		return apartment;
	}

	public Apartment removeApartment(Apartment apartment) {
		getApartments().remove(apartment);
		apartment.setImage(null);

		return apartment;
	}

	public List<ImagesApartment> getImagesApartments() {
		return this.imagesApartments;
	}

	public void setImagesApartments(List<ImagesApartment> imagesApartments) {
		this.imagesApartments = imagesApartments;
	}

	public ImagesApartment addImagesApartment(ImagesApartment imagesApartment) {
		getImagesApartments().add(imagesApartment);
		imagesApartment.setImage(this);

		return imagesApartment;
	}

	public ImagesApartment removeImagesApartment(ImagesApartment imagesApartment) {
		getImagesApartments().remove(imagesApartment);
		imagesApartment.setImage(null);

		return imagesApartment;
	}

}