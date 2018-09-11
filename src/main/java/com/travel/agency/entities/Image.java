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
	
	@ManyToMany(mappedBy = "images")
	private List<Accommodation> accommodations;
	
	//bi-directional many-to-one association to ImagesAccommodation
	@OneToMany(mappedBy="image")
	private List<ImagesAccommodation> imagesAccommodations;
	
    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }
	
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

	public List<ImagesAccommodation> getImagesAccommodations() {
		return this.imagesAccommodations;
	}

	public void setImagesAccommodations(List<ImagesAccommodation> imagesAccommodations) {
		this.imagesAccommodations = imagesAccommodations;
	}

	public ImagesAccommodation addImagesAccommodation(ImagesAccommodation imagesAccommodation) {
		getImagesAccommodations().add(imagesAccommodation);
		imagesAccommodation.setImage(this);

		return imagesAccommodation;
	}

	public ImagesAccommodation removeImagesAccommodation(ImagesAccommodation imagesAccommodation) {
		getImagesAccommodations().remove(imagesAccommodation);
		imagesAccommodation.setImage(null);

		return imagesAccommodation;
	}

}