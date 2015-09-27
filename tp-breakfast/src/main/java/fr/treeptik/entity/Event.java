package fr.treeptik.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "event")
@Inheritance(strategy = InheritanceType.JOINED)
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	Date date;
	
	@Column
	String comment;
	
	@ManyToOne
	@JoinColumn(name="idorganizer")
	private Member organizer;
	
	@OneToMany(mappedBy = "event")
	private Set<Diary> diaries;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Member getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Member organizer) {
		this.organizer = organizer;
	}

	public Set<Diary> getDiaries() {
		return diaries;
	}

	public void setDiaries(Set<Diary> diaries) {
		this.diaries = diaries;
	}

	
	
	
}
