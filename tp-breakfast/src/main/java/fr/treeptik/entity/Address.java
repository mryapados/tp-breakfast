package fr.treeptik.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;


@Embeddable
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	private String street;
	private String town;
	private String zip;
	
	public Address(String street, String tawn, String zip) {
		super();
		this.street = street;
		this.town = tawn;
		this.zip = zip;
	}
	
	public Address() {
		super();
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
