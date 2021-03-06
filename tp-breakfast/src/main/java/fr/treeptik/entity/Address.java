package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Embeddable
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String street;
	
	@Column
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	private String town;
	
	@Column
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
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
