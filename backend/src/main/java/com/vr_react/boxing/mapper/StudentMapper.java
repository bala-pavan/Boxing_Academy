package com.vr_react.boxing.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.vr_react.boxing.dto.StudentDTO;
import com.vr_react.boxing.util.Base64Check;

public class StudentMapper extends BeanPropertyRowMapper<StudentDTO>{
		public StudentDTO mapRow(ResultSet rs, int rowNumber) throws SQLException{
			Base64Check base64Check =  new Base64Check();
			
			StudentDTO dto = new StudentDTO();
			dto.setStudentid(rs.getInt("studentid"));
			dto.setStudentname(base64Check.decodeBase64(rs.getString("studentname")));
			dto.setStudentdob(rs.getDate("studentdob"));
			dto.setAddress(rs.getString("address"));
			dto.setAge(rs.getInt("age"));
			dto.setMoblie(rs.getString("moblie"));
			return dto;
			
		}
		

}