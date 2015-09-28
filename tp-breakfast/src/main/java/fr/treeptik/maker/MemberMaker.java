package fr.treeptik.maker;

import java.io.Serializable;

import fr.treeptik.entity.Ingredient;

public class MemberMaker implements Serializable {

	private static final long serialVersionUID = 1L;

	//User
	private Integer id;
	private String login;
	private String password;
	private String passwordMatch;
	private Boolean enabled;
	private String role;
	//Member
	private String firstName;
	private String lastName;
	private Ingredient.BreakfastType preference;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordMatch() {
		return passwordMatch;
	}
	public void setPasswordMatch(String passwordMatch) {
		this.passwordMatch = passwordMatch;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public Ingredient.BreakfastType getPreference() {
		return preference;
	}
	public void setPreference(Ingredient.BreakfastType preference) {
		this.preference = preference;
	}
	
	

	

}
