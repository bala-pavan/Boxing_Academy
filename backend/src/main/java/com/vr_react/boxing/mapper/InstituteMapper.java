package com.vr_react.boxing.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.vr_react.boxing.dto.InstituteDTO;
import com.vr_react.boxing.util.Base64Check;

public class InstituteMapper extends BeanPropertyRowMapper<InstituteDTO>{
		public InstituteDTO mapRow(ResultSet rs, int rowNumber) throws SQLException{
			Base64Check base64Check =  new Base64Check();
			
			InstituteDTO dto = new InstituteDTO();
			dto.setInstituteid(rs.getInt("courseid"));
			dto.setInstitutename(base64Check.decodeBase64(rs.getString("institutename")));
			dto.setInstitutedescription(rs.getString("Institutedescription"));
			dto.setInstituteaddress(rs.getString("instituteaddress"));
			dto.setEmail(rs.getString("email"));
			dto.setMoblie(rs.getString("moblie"));
			return dto;
			
		}
		

}