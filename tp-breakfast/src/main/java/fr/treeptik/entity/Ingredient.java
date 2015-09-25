package fr.treeptik.entity;

import java.io.Serializable;
import java.util.List;

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

	public enum breakfastType{
		SWEET, SALTED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	String name;
	
	@Column
	@Enumerated(EnumType.STRING)
	breakfastType type;
	
	@ManyToMany(mappedBy = "teams")
	private List<Breakfast> breakfasts;

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

	public breakfastType getType() {
		return type;
	}

	public void setType(breakfastType type) {
		this.type = type;
	}

	public List<Breakfast> getBreakfasts() {
		return breakfasts;
	}

	public void setBreakfasts(List<Breakfast> breakfasts) {
		this.breakfasts = breakfasts;
	}
	
	
	
	
}
