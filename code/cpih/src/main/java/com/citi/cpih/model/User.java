package com.citi.cpih.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author jorge.ruiz citi.com.mx
 */
@Entity(name = "User")
@Table(name = "PIH_USER_CONSOLE")
public class User implements Serializable {
	
	private static final long serialVersionUID = -4360789996220174816L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PIH_USER_CONSOLE")
    @SequenceGenerator(name = "SEQ_PIH_USER_CONSOLE", sequenceName = "SEQ_PIH_USER_CONSOLE", allocationSize = 1, initialValue = 1)
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ROLE")
	private Integer role;
	
	@Transient
	private Boolean encodedPass;
	
	public User() {
		
	}
	
	public User(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Boolean getEncodedPass() {
		return encodedPass;
	}

	public void setEncodedPass(Boolean encodedPass) {
		this.encodedPass = encodedPass;
	}

}