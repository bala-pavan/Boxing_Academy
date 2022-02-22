package com.vr_react.boxing.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr_react.boxing.dto.LoginDTO;
import com.vr_react.boxing.dto.UserDTO;
import com.vr_react.boxing.mapper.UserMapper;
import com.vr_react.boxing.util.Base64Check;
import com.vr_react.boxing.util.UserUtil;

@RestController
@RequestMapping("/admin" )
public class AdminController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	private Base64Check base64Check;
	
	Logger logger = LogManager.getLogger(AdminController.class);
	
	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public String signup(@RequestBody String userString) {
		
		logger.info("signup admin method start");
		
		JSONObject jsonObject = new JSONObject(userString);
		
		String email = jsonObject.getString("email");
		String password = jsonObject.getString("password");
		String moblieNumber = jsonObject.getString("moblieNumber");
		String sAdmin = "ADMIN";
		
		
		String userName = userUtil.createId()+"A";
		
		int temp = 0;

		//checking with null and empty
		if ((email == null || email.isEmpty())
				|| (password == null || password.isEmpty())
				|| (moblieNumber == null || moblieNumber.isEmpty())) {
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
		try {
			
			//query to insert
			String query = "insert into User(email, password, username, moblie_Number, user_Role) values (?, ?, ?, ?, ?)";

			//if update in sql temp will be 1
			temp = jdbcTemplate.update(query, email, password, userName, moblieNumber, sAdmin);
		} catch (Exception e) {
			logger.error(e.getMessage());
			//if any error occurs
			return e.getMessage();
		}
		logger.info("signup admin method end");
		return (temp == 1) ? "true" : "false";

	}
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody LoginDTO loginDTO) {

		logger.info("login admin method start");
		

		// checking with null and empty
		if ((loginDTO.getEmail() == null || loginDTO.getEmail().isEmpty())
				|| (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty())) {
			return "You are Entering Null values";
		}
		
		
		// checking with email is proper or not
		if (!userUtil.isProperEmail(loginDTO.getEmail())) {
			return "check with email";
		}

		String query = "select 1 from user where email = ? and password = ? and user_role = 'ADMIN' limit 1";
		
		
		Integer isLogin = 0;

		try {
			isLogin = jdbcTemplate.queryForObject(query, new Object[] { loginDTO.getEmail(), loginDTO.getPassword() },
					Integer.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return e.getMessage();
		}

		logger.info("login admin method end");
		
		return (isLogin == 1) ? "true" : "false";
	}

	
	@PostMapping(value = "/delete")
	public String delete(@RequestBody String jsonUser) {
		logger.info("delete admin method start");
		JSONObject jsonObject = new JSONObject(jsonUser);
		String email = jsonObject.getString("email");
		
		int temp = 0;
		try {
			String query = "delete user where email = ? and user_role= 'NONADMIN'";
			temp = jdbcTemplate.update(query,email);
		}catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return e.getMessage();
		}
		logger.info("delete admin method end");
		return (temp == 1)? "true": "false";
	}
	
	@PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
	public String edit(@RequestBody String userString) {
		
		logger.info("edit admin method start");
		
		JSONObject jsonObject = new JSONObject(userString);
		
		String email = jsonObject.getString("email");
		String password = jsonObject.optString("password");
		String moblieNumber = jsonObject.optString("moblieNumber");
		Integer userRole = jsonObject.optInt("userRole");
		String change = jsonObject.getString("change");
		String sUserRole = null;
		if(userRole != null && userRole == 0) {
			sUserRole = "NONADMIN";
		}
		else if(userRole != null && userRole == 1) {
			sUserRole = "ADMIN";
		}
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
		String query = new String();
		
		
		try {
			
			if(change.equals("password")) {
				query = "update user set password = ? where email = ?";
				temp = jdbcTemplate.update(query, password, email);
			}else if (change.equals("moblieNumber")) {
				query = "update user set moblie_number = ? where email = ?";
				temp = jdbcTemplate.update(query, moblieNumber, email);
			}else if (change.equals("userRole")) {
				query = "update user set user_role = ? where email = ?";
				temp = jdbcTemplate.update(query, sUserRole, email);
			}
			else if(change.equals("all")) {
				query = "update user set password = ?, moblie_number = ? where email = ?";
				temp = jdbcTemplate.update(query, password, moblieNumber, email);
			}
			
			//if update in sql temp will be 1
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			//if any error occurs
			return e.getMessage();
		}
		logger.info("edit admin method end");
		return (temp == 1) ? "true" : "false";

	}
	@PostMapping(value = "/display")
	public List<UserDTO> display() {
		
		String query = "select * from user";
		List<UserDTO> userDTOs = null;
		try {
			 userDTOs = jdbcTemplate.query(query, new UserMapper());
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return userDTOs;
	}
	
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/singleDisplay")
	public List<UserDTO> singledisplay(@RequestBody String jsonBody) {
		
		String query = "select * from user where email=? and user_role = 'ADMIN' limit 1";
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
