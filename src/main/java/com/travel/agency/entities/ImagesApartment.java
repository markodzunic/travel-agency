package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the images_apartment database table.
 * 
 */
@Entity
@Table(name="images_apartment")
@NamedQuery(name="ImagesApartment.findAll", query="SELECT i FROM ImagesApartment i")
public class ImagesApartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="images_id")
	private Image image;

	//bi-directional many-to-one association to Apartment
	@ManyToOne
	@JoinColumn(name="apartments_id")
	private Apartment apartment;

	public ImagesApartment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Apartment getApartment() {
		return this.apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

}