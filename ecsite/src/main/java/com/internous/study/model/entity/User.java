package com.internous.study.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="is_admin")
	private int isAdmin;
	


public long getId() {
	return id;
}

public void serId(long id) {
	this.id = id;
}

public String getUserName() {
	return userName;
}

public void serUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void serPassword(String password) {
	this.password  = password;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public int getIsAdmin() {
	return isAdmin;
}

public void serIsAdmin(int isAdmin) {
	this.isAdmin = isAdmin;
}

}