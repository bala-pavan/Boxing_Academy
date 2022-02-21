package com.vr_react.boxing.dto;

import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;


@Entity

@Table(name = "Login")
public class LoginDTO {

	@Id
	public String email;
	public String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
