package com.vr_react.boxing.dto;


import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;  

@Entity

@Table(name = "User")
public class UserDTO {
	@Id
	
	public String email;
	public String password; 
	public String username;
	public String moblieNumber;
	public String userRole;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMoblieNumber() {
		return moblieNumber;
	}
	public void setMoblieNumber(String moblieNumber) {
		this.moblieNumber = moblieNumber;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	

}
