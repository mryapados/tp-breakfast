package fr.treeptik.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum BreakfastType{
		SWEET, SALTED, BOTH
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	String name;
	
	@Column
	@Enumerated(EnumType.STRING)
	BreakfastType type;
	
	@ManyToMany(mappedBy = "ingredients")
	private Set<Breakfast> breakfasts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BreakfastType getType() {
		return type;
	}

	public void setType(BreakfastType type) {
		this.type = type;
	}

	public Set<Breakfast> getBreakfasts() {
		return breakfasts;
	}

	public void setBreakfasts(Set<Breakfast> breakfasts) {
		this.breakfasts = breakfasts;
	}
	
	
	
	
}
