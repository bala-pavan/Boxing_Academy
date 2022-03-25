package com.vr_react.boxing.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vr_react.boxing.dto.LoginDTO;
import com.vr_react.boxing.dto.UserDTO;
import com.vr_react.boxing.mapper.UserMapper;
import com.vr_react.boxing.util.Base64Check;
import com.vr_react.boxing.util.UserUtil;
@CrossOrigin
@RestController
@RequestMapping("/user" )
public class UserController {


	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	private Base64Check base64Check;
	
	Logger logger = LogManager.getLogger(UserController.class);
	
	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public String signup(@RequestBody String userString) {
		
		logger.info("signupuser  method start");
		
		JSONObject jsonObject = new JSONObject(userString);
		
		String email = jsonObject.getString("email");
		String password = jsonObject.getString("password").trim();
		String confirmPassword=jsonObject.getString("confirmPassword").trim();
		String moblieNumber = jsonObject.getString("mobileNumber");
		String sAdmin =  "NONADMIN";
		
		
		String userName = userUtil.createId(); 
		
		int temp = 0;

		//checking with null and empty
		if ((email == null || email.isEmpty())
				|| (email == null || email.isEmpty())) {
			return "You are Entering Null values";
		}
		
		if(moblieNumber.length() != 10) {
			return "Moblie number have to be 10 digits";
		}

		//checking with email is proper or not
		if (!userUtil.isProperEmail(email)) {
			return "check with email";
		}
		if (!password.equals(confirmPassword))
		{
			return "Password mismatch";
		}
		
		// checking if string is base64 or not
				if (!base64Check.checkForEncode(password)) {
					// converting into base64
					password = base64Check.encodeBase64(password);
				}
		try {
			
			//query to insert
			String query = "insert into user(email, password, username, moblie_Number, user_Role) values (?, ?, ?, ?, ?)";

			//if update in sql temp will be 1
			temp = jdbcTemplate.update(query, email, password, userName, moblieNumber, sAdmin);
		} catch (Exception e) {
			logger.error(e.getMessage());
			//if any error occurs
			return "false";
		}
		logger.info("signup method end");
		return (temp == 1) ? "true" : "false";

	}
	
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody LoginDTO loginDTO) {

		logger.info("signup method start");
		int temp = 0;

		// checking with null and empty
		if ((loginDTO.getEmail() == null || loginDTO.getEmail().isEmpty())
				|| (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty())) {
			return "You are Entering Null values";
		}
		

		// checking with email is proper or not
		if (!userUtil.isProperEmail(loginDTO.getEmail())) {
			return "check with email";
		}
		if (!base64Check.checkForEncode(loginDTO.getPassword())) {
			// converting into base64
			loginDTO.setPassword(base64Check.encodeBase64(loginDTO.getPassword()));
		}
		
		String query = "select 1 from user where email = ? and password = ? and user_role = 'NONADMIN' limit 1";
		
		
		Integer isLogin = 0;

		try {
//		jdbcTemplate.query(query, new MapSqlParameterSource().addValue("email", loginDTO.getEmail()).addValue("password", loginDTO.getPassword()));
			isLogin = jdbcTemplate.queryForObject(query, new Object[] { loginDTO.getEmail(), loginDTO.getPassword() },
					Integer.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
		}

		return (isLogin == 1) ? "true" : "false";
	}
	
	@PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
	public String edit(@RequestBody String userString) {
		
		logger.info("edit method start");
		
		JSONObject jsonObject = new JSONObject(userString);
		
		String email = jsonObject.getString("email");
		String password = jsonObject.optString("password");
		String moblieNumber = jsonObject.optString("moblieNumber");
		
		String change = jsonObject.optString("change");
		
		int temp = 0;

		//checking with null and empty
		if ((email == null || email.isEmpty())) {
			return "You are Entering Null values";
		}

		//checking with email is proper or not
		if (!userUtil.isProperEmail(email)) {
			return "check with email";
		}
		
		//checking if string is base64 or not
		if(!base64Check.checkForEncode(password)) {
			//converting into base64
			password = base64Check.encodeBase64(password);
		}
		String query = null;
		
		
		try {
			
			if(change.equals("password")) {
				query = "update user set password = ? where email = ? and user_role = 'NONADMIN'";
				temp = jdbcTemplate.update(query, password, email);
			}else if (change.equals("moblieNumber")) {
				query = "update user set moblie_number = ? where email = ? and user_role = 'NONADMIN'";
				temp = jdbcTemplate.update(query, moblieNumber, email);
			}
			else if(change.equals("all")) {
				query = "update user set password = ?, moblie_number = ? where email = ? and user_role = 'NONADMIN'";
				temp = jdbcTemplate.update(query, password, moblieNumber, email);
			}
			//if update in sql temp will be 1
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			//if any error occurs
			return "false";
		}
		logger.info("signup method end");
		return (temp == 1) ? "true" : "false";

	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/display")
	public List<UserDTO> display(@RequestBody String jsonBody) {
		
		String query = "select * from user where email=? and user_role = 'NONADMIN' limit 1";
		List<UserDTO> userDTOs = null;
		try {
			 JSONObject jsonObject=new JSONObject(jsonBody);

			userDTOs = jdbcTemplate.query(query, new String[] { jsonObject.getString("email") },new UserMapper());
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return userDTOs;
	}
	
}
