package com.vr_react.boxing.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.vr_react.boxing.dto.CourseDTO;
import com.vr_react.boxing.util.Base64Check;

public class CourseMapper extends BeanPropertyRowMapper<CourseDTO>{
		public CourseDTO mapRow(ResultSet rs, int rowNumber) throws SQLException{
			Base64Check base64Check =  new Base64Check();
			
			CourseDTO dto = new CourseDTO();
			dto.setCourseid(rs.getInt("courseid"));
			dto.setCoursename(base64Check.decodeBase64(rs.getString("coursename")));
			dto.setCoursedescription(rs.getString("coursedescription"));
			dto.setCourseduration(rs.getInt("courseduration"));
			return dto;
			
		}
		

}
