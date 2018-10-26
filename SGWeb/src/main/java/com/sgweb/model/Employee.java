package com.sgweb.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Employee extends Person {

    private static final long serialVersionUID = 1L;
    private String User;
    private String Password;
    private Boolean Admin;
    private Boolean Logged;
    
    
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Boolean getAdmin() {
		return Admin;
	}
	public void setAdmin(Boolean admin) {
		Admin = admin;
	}
	public Boolean getLogged() {
		return Logged;
	}
	public void setLogged(Boolean logged) {
		Logged = logged;
	}

    
}
