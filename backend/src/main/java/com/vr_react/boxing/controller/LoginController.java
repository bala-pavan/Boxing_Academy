package com.vr_react.boxing.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vr_react.boxing.dto.LoginDTO;
import com.vr_react.boxing.util.UserUtil;

@RestController
public class LoginController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserUtil userUtil;

	Logger logger = LogManager.getLogger(LoginController.class);

	@GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public String test() {

		logger.info("this is test logger");
		logger.error("this is logger");
		return "This is Home page";
	}
//	@PostMapping(value="/test2", consumes  = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
//    public String test2(@RequestBody String S) {
//
//		JSONObject jsonObject = new JSONObject(S);
//        return jsonObject.optString("value");
//    
//	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody LoginDTO loginDTO, @RequestParam(required = false)  Integer admin) {

		logger.info("signup method start");
		int temp = 0;

		// checking with null and empty
		if ((loginDTO.getEmail() == null || loginDTO.getEmail().isEmpty())
				|| (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty())) {
			return "You are Entering Null values";
		}
		
		if(admin == null || admin == 0) {
			admin = 0;
		}
		else {
			admin = 1;
		}

		// checking with email is proper or not
		if (!userUtil.isProperEmail(loginDTO.getEmail())) {
			return "check with email";
		}

		String query = new String();
		if(admin == 0) {
			query = "select 1 from user where email = ? and password = ? and user_role = 'NONADMIN' limit 1";
		}
		else if(admin == 1){
			query = "select 1 from user where email = ? and password = ? and user_role = 'ADMIN' limit 1";
		}
		
		Integer isLogin = 0;

		try {
//		jdbcTemplate.query(query, new MapSqlParameterSource().addValue("email", loginDTO.getEmail()).addValue("password", loginDTO.getPassword()));
			isLogin = jdbcTemplate.queryForObject(query, new Object[] { loginDTO.getEmail(), loginDTO.getPassword() },
					Integer.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return e.getMessage();
		}

		return (isLogin == 1) ? "true" : "false";
	}

}
