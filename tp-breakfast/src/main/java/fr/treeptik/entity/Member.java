package fr.treeptik.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "member")
public class Member extends User {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "first_name")
	String firstName;
	
	@Column(name = "last_name")
	String lastName;
	
	@OneToMany(mappedBy = "organizer")
	private Set<Event> events;

	@OneToMany(mappedBy = "member")
	private Set<Diary> diaries;

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
	
	
	
}