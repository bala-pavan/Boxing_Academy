package com.vr_react.boxing.dto;

import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;


@Entity

@Table(name = "Course")
public class CourseDTO {
	@Id
	int courseid;
	String coursename;
	String coursedescription;
	int courseduration;
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCoursedescription() {
		return coursedescription;
	}
	public void setCoursedescription(String coursedescription) {
		this.coursedescription = coursedescription;
	}
	public int getCourseduration() {
		return courseduration;
	}
	public void setCourseduration(int courseduration) {
		this.courseduration = courseduration;
	}
	
}
