package fr.treeptik.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Entity
@Table(name = "breakfast")
@PrimaryKeyJoinColumn(name = "id")
public class Breakfast extends Event {

	private static final long serialVersionUID = 1L;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotNull
	@Column
	private String name;
	
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
