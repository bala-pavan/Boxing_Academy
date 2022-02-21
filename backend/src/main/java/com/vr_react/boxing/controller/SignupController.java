package com.vr_react.boxing.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.Parameter;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vr_react.boxing.dto.LoginDTO;
import com.vr_react.boxing.dto.UserDTO;
import com.vr_react.boxing.util.Base64Check;
import com.vr_react.boxing.util.UserUtil;

@RestController
public class SignupController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	private Base64Check base64Check;
	
	Logger logger = LogManager.getLogger(SignupController.class);

	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public String signup(@RequestBody String userString, @RequestParam(required = false) Integer admin) {
		
		logger.info("signup method start");
		
		JSONObject jsonObject = new JSONObject(userString);
		
		String email = jsonObject.getString("email");
		String password = jsonObject.getString("password");
		String moblieNumber = jsonObject.getString("moblieNumber");
		String sAdmin = new String();
		if(admin == null || admin == 0) {
			sAdmin = "NONADMIN";
		}
		else {
			sAdmin = "ADMIN";
		}
		
		
		String userName = userUtil.createId(); 
		
		int temp = 0;

		//checking with null and empty
		if ((email == null || email.isEmpty())
				|| (email == null || email.isEmpty())) {
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
		logger.info("signup method end");
		return (temp == 1) ? "true" : "false";

	}

	
	

}
