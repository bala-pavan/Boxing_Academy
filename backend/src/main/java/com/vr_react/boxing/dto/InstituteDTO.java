package com.vr_react.boxing.dto;

import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;


@Entity

@Table(name = "Institute")
public class InstituteDTO {
	@Id
	int instituteid;
	String institutename;
	String institutedescription;
	String instituteaddress;
	String moblie;
	String email;
	String imageurl;
	
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public int getInstituteid() {
		return instituteid;
	}
	public void setInstituteid(int instituteid) {
		this.instituteid = instituteid;
	}
	public String getInstitutename() {
		return institutename;
	}
	public void setInstitutename(String institutename) {
		this.institutename = institutename;
	}
	public String getInstitutedescription() {
		return institutedescription;
	}
	public void setInstitutedescription(String institutedescription) {
		this.institutedescription = institutedescription;
	}
	public String getInstituteaddress() {
		return instituteaddress;
	}
	public void setInstituteaddress(String instituteaddress) {
		this.instituteaddress = instituteaddress;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
