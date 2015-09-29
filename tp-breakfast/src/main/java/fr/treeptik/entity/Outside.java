package fr.treeptik.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "outside") // sortie : organisation de match, soirée, ...
public class Outside extends Event {

	private static final long serialVersionUID = 1L;
	
	public enum OutType{
		SPORT, EVENING
	}
	
	@Column
	@Enumerated(EnumType.STRING)
	private OutType type;

	@Embedded
	private Address adress;

	public OutType getType() {
		return type;
	}

	public void setType(OutType type) {
		this.type = type;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

}
