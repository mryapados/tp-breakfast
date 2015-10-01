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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

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
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@NotNull
	@Column
	String name;
	
	@NotNull
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
