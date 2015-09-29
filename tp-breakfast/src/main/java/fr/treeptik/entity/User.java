package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "status")
@DiscriminatorValue(value = "user")
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "login")
	private String login;

	@Column(name = "encrypted_password")
	private String encryptedPassword;

	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "role")
	private String role;

	@Transient
	private String name;
	
	abstract String getName();
	
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptedPassword = encryptPassword;
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
	


}
