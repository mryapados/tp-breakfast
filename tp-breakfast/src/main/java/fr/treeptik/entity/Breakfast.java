package fr.treeptik.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "breakfast")
@PrimaryKeyJoinColumn(name = "id")
public class Breakfast extends Event {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column
	String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "compose", 
		joinColumns = { 
			@JoinColumn(name = "idbreakfast", 
			referencedColumnName = "id")}, 
		inverseJoinColumns = {
			@JoinColumn(name = "idingredient", 
			referencedColumnName = "id")})
	private Set<Ingredient> ingredients;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Ingredient> getIngredients() {
		if (this.ingredients == null) this.ingredients = new HashSet<>(); 
		return this.ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	
	
	
	
	
}
