package com.vr_react.boxing.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.vr_react.boxing.dto.UserDTO;
import com.vr_react.boxing.util.Base64Check;

public class UserMapper extends BeanPropertyRowMapper<UserDTO>{
	public UserDTO mapRow(ResultSet rs, int rowNumber) throws SQLException{
		Base64Check base64Check =  new Base64Check();
		
		UserDTO dto = new UserDTO();
		dto.setEmail(rs.getString("email"));
		dto.setPassword(base64Check.decodeBase64(rs.getString("password")));
		dto.setUsername(rs.getString("username"));
		dto.setMoblieNumber(rs.getString("moblie_number"));
		dto.setUserRole(rs.getString("user_role"));
		return dto;
		
	}
	

}
