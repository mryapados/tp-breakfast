package fr.treeptik.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@DiscriminatorValue(value = "member")
public class Member extends User {

	private static final long serialVersionUID = 1L;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "first_name")
	String firstName;
	
	@SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
	@Column(name = "last_name")
	String lastName;
	
	@Column(name = "preference")
	@Enumerated(EnumType.STRING)
	Ingredient.BreakfastType preference;
	
	@OneToMany(mappedBy = "organizer")
	private Set<Event> events;

	@OneToMany(mappedBy = "member")
	private Set<Diary> diaries;

	@Override
	public String getName() {
		return (firstName + " " + lastName).trim();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Diary> getDiaries() {
		return diaries;
	}

	public void setDiaries(Set<Diary> diaries) {
		this.diaries = diaries;
	}

	public Ingredient.BreakfastType getPreference() {
		return preference;
	}

	public void setPreference(Ingredient.BreakfastType preference) {
		this.preference = preference;
	}
	
	
	
}
