package com.vr_react.boxing.dto;
import java.util.Date;

import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;


@Entity

@Table(name = "Student")
public class StudentDTO {
	
	@Id
	int studentid;
	String studentname;
	Date studentdob;
	String address;
	String moblie;
	int age;
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public Date getStudentdob() {
		return studentdob;
	}
	public void setStudentdob(Date studentdob) {
		this.studentdob = studentdob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
