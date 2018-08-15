package com.travel.agency.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the images_accommodations database table.
 * 
 */
@Entity
@Table(name="images_accommodations")
@NamedQuery(name="ImagesAccommodation.findAll", query="SELECT i FROM ImagesAccommodation i")
public class ImagesAccommodation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Accommodation
	@OneToMany(mappedBy="imagesAccommodation")
	private List<Accommodation> accommodations;

	//bi-directional many-to-one association to Accommodation
	@ManyToOne
	@JoinColumn(name="accommodations_id")
	private Accommodation accommodation;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="images_id")
	private Image image;

	public ImagesAccommodation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Accommodation> getAccommodations() {
		return this.accommodations;
	}

	public void setAccommodations(List<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}

	public Accommodation addAccommodation(Accommodation accommodation) {
		getAccommodations().add(accommodation);
		accommodation.setImagesAccommodation(this);

		return accommodation;
	}

	public Accommodation removeAccommodation(Accommodation accommodation) {
		getAccommodations().remove(accommodation);
		accommodation.setImagesAccommodation(null);

		return accommodation;
	}

	public Accommodation getAccommodation() {
		return this.accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}