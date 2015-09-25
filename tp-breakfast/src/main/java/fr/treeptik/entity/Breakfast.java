package fr.treeptik.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "breakfast")
public class Breakfast implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Float prix;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "compose", 
		joinColumns = { 
			@JoinColumn(name = "breakfast", 
			referencedColumnName = "id")}, 
		inverseJoinColumns = {
			@JoinColumn(name = "ingredient", 
			referencedColumnName = "id")})
	private List<Ingredient> ingredients;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
	
}
