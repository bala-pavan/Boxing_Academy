package com.vr_react.boxing.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.vr_react.boxing.dto.CourseDTO;
import com.vr_react.boxing.dto.InstituteDTO;
import com.vr_react.boxing.dto.LoginDTO;
import com.vr_react.boxing.dto.StudentDTO;
import com.vr_react.boxing.dto.UserDTO;
import com.vr_react.boxing.mapper.CourseMapper;
import com.vr_react.boxing.mapper.InstituteMapper;
import com.vr_react.boxing.mapper.StudentMapper;
import com.vr_react.boxing.mapper.UserMapper;
import com.vr_react.boxing.util.Base64Check;
import com.vr_react.boxing.util.UserUtil;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/admin")
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
		String password = jsonObject.getString("password").trim();
		String confirmPassword = jsonObject.getString("confirmPassword").trim();
		String moblieNumber = jsonObject.getString("mobileNumber");
		String sAdmin = "ADMIN";

		String userName = userUtil.createId() + "A";

		int temp = 0;

		// checking with null and empty
		if ((email == null || email.isEmpty()) || (password == null || password.isEmpty())
				|| (moblieNumber == null || moblieNumber.isEmpty())) {
			return "You are Entering Null values";
		}

		if (moblieNumber.length() != 10) {
			return "Moblie number have to be 10 digits";
		}

		// checking with email is proper or not
		if (!userUtil.isProperEmail(email)) {
			return "check with email";
		}
		if (password == confirmPassword) {
			return "Password mismatch";
		}

		// checking if string is base64 or not
		if (!base64Check.checkForEncode(password)) {
			// converting into base64
			password = base64Check.encodeBase64(password);
		}
		try {

			// query to insert
			String query = "insert into User(email, password, username, moblie_Number, user_Role) values (?, ?, ?, ?, ?)";

			// if update in sql temp will be 1
			temp = jdbcTemplate.update(query, email, password, userName, moblieNumber, sAdmin);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
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
			return "false";
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
			temp = jdbcTemplate.update(query, email);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
		}
		logger.info("delete admin method end");
		return (temp == 1) ? "true" : "false";
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
		if (userRole != null && userRole == 0) {
			sUserRole = "NONADMIN";
		} else if (userRole != null && userRole == 1) {
			sUserRole = "ADMIN";
		}
		int temp = 0;

		// checking with null and empty
		if ((email == null || email.isEmpty())) {
			return "You are Entering Null values";
		}

		// checking with email is proper or not
		if (!userUtil.isProperEmail(email)) {
			return "check with email";
		}

		// checking if string is base64 or not
		if (!base64Check.checkForEncode(password)) {
			// converting into base64
			password = base64Check.encodeBase64(password);
		}
		String query = new String();

		try {

			if (change.equals("password")) {
				query = "update user set password = ? where email = ?";
				temp = jdbcTemplate.update(query, password, email);
			} else if (change.equals("moblieNumber")) {
				query = "update user set moblie_number = ? where email = ?";
				temp = jdbcTemplate.update(query, moblieNumber, email);
			} else if (change.equals("userRole")) {
				query = "update user set user_role = ? where email = ?";
				temp = jdbcTemplate.update(query, sUserRole, email);
			} else if (change.equals("all")) {
				query = "update user set password = ?, moblie_number = ? where email = ?";
				temp = jdbcTemplate.update(query, password, moblieNumber, email);
			}

			// if update in sql temp will be 1

		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
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
		} catch (Exception e) {
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
			JSONObject jsonObject = new JSONObject(jsonBody);

			userDTOs = jdbcTemplate.query(query, new String[] { jsonObject.getString("email") }, new UserMapper());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return userDTOs;
	}

	// course
	@PostMapping(value = "/addcourse")
	public String addcourse(@RequestBody CourseDTO courseDTO) {
		logger.info("add course method start");
		
		
		courseDTO.setCourseid(Integer.parseInt(userUtil.generateId()));
		int courseid = courseDTO.getCourseid();
		String coursename = courseDTO.getCoursename();
		String coursedescription = courseDTO.getCoursedescription();
		int courseduration = courseDTO.getCourseduration();
		
		
		String query = "insert into course (COURSEID,COURSEDESCRIPTION, COURSEDURATION, COURSENAME )  values (?, ?, ?, ?)";

		int temp = 0;

		try {
			temp = jdbcTemplate.update(query, courseid, coursedescription, courseduration, coursename);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
		}

		logger.info("add course method end");

		return (temp == 1) ? "true" : "false";
	}

	@PostMapping(value = "/editcourse")
	public String editcourse(@RequestBody String userString) {
	
	logger.info("edit course admin method start");

	JSONObject jsonObject = new JSONObject(userString);

	
	
	int courseid = jsonObject.getInt("courseid");
	String coursename = jsonObject.optString("coursename");
	String coursedescription = jsonObject.optString("coursedesprition");
	int courseduration = jsonObject.getInt("courseduration");

	String change = jsonObject.getString("change");
	
	int temp = 0;
	
	String query = new String();

	try {

		if (change.equals("coursename")) {
			query = "update course set coursename = ? where courseid = ?";
			temp = jdbcTemplate.update(query, coursename, courseid);
		} else if (change.equals("coursedescription")) {
			query = "update course set coursedescription = ? where courseid = ?";
			temp = jdbcTemplate.update(query,coursedescription, courseid);
		}

		// if update in sql temp will be 1

	} catch (Exception e) {
		logger.error(e.getMessage());
		// if any error occurs
		return "false";
	}
	logger.info("edit course admin method end");
	return (temp == 1) ? "true" : "false";

	}

	@PostMapping(value = "/deletecourse")
	public String deletecourse(@RequestBody String jsonUser) {
		logger.info("delete course method start");
		JSONObject jsonObject = new JSONObject(jsonUser);
		int courseid = jsonObject.getInt("courseid");
		

		int temp = 0;
		try {
			String query = "delete from course where courseid = ? ";
			temp = jdbcTemplate.update(query, courseid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
		}
		logger.info("delete course method end");
		return (temp == 1) ? "true" : "false";
	}

	@PostMapping(value = "/viewcourse")
	public List<CourseDTO> viewcourse() {

		String query = "select * from course";
		List<CourseDTO> courseDTOs = null;
		try {
			courseDTOs = jdbcTemplate.query(query, new CourseMapper());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courseDTOs;
	}

	// institute
	@PostMapping(value = "/addinstitute")
	public String addinstitute(@RequestBody InstituteDTO instituteDTO) {
		logger.info("add institute method start");
		
		instituteDTO.setInstituteid(Integer.parseInt(userUtil.generateId()));
		int instituteid = instituteDTO.getInstituteid();
		String institutename = instituteDTO.getInstitutename();
		String institutedescription = instituteDTO.getInstitutedescription();
		String instituteaddress = instituteDTO.getInstituteaddress();
		String moblie=instituteDTO.getMoblie();
		String email=instituteDTO.getEmail();
		
		String query = "insert into institute (INSTITUTEID, EMAIL, INSTITUTEADDRESS, INSTITUTEDESCRIPTION, INSTITUTENAME, MOBLIE)  values (?, ?, ?, ?, ? , ?)";
		
		int temp = 0;

		try {
			temp = jdbcTemplate.update(query, instituteid,email, instituteaddress, institutedescription, institutename, email);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
		}

		logger.info("add institute method end");

		return (temp == 1) ? "true" : "false";
	}

	@PostMapping(value = "/editinstitute")
	public String editinstitute(@RequestBody String userString) {
	
	logger.info("edit institute admin method start");

	JSONObject jsonObject = new JSONObject(userString);

	int instituteid = jsonObject.getInt("instituteid");
	String institutename = jsonObject.getString("institutename");
	String institutedescription = jsonObject.getString("institutedescription");
	String instituteaddress = jsonObject.getString("instituteaddress");
	String moblie=jsonObject.getString("moblie");
	String email=jsonObject.getString("email");
	
	String change = jsonObject.getString("change");
	
	int temp = 0;
	
	String query = new String();

	try {

		if (change.equals("institutename")) {
			query = "update institute set institutename = ? where instituteid = ?";
			temp = jdbcTemplate.update(query, institutename, instituteid);
		} else if (change.equals("institutedescription")) {
			query = "update institute set coursedescription = ? where instituteid = ?";
			temp = jdbcTemplate.update(query,institutedescription, instituteid);
		}
		else if (change.equals("instituteaddress")) {
			query = "update institute set instituteaddress = ? where instituteid = ?";
			temp = jdbcTemplate.update(query,instituteaddress, instituteid);
		}
		else if (change.equals("moblie")) {
			query = "update institute set moblie = ? where instituteid = ?";
			temp = jdbcTemplate.update(query,moblie, instituteid);
		}

		// if update in sql temp will be 1

	} catch (Exception e) {
		logger.error(e.getMessage());
		// if any error occurs
		return "false";
	}
	logger.info("edit course admin method end");
	return (temp == 1) ? "true" : "false";

	}

	@PostMapping(value = "/deleteinstitute")
	public String deleteinstitute(@RequestBody String jsonUser) {
		logger.info("delete institutee method start");
		JSONObject jsonObject = new JSONObject(jsonUser);
		int instituteid = jsonObject.getInt("instituteid");
		

		int temp = 0;
		try {
			String query = "delete from institute where instituteid = ? ";
			temp = jdbcTemplate.update(query, instituteid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
		}
		logger.info("delete institute method end");
		return (temp == 1) ? "true" : "false";
	}

	@PostMapping(value = "/viewinstitute")
	public List<InstituteDTO> viewinstitute() {

		String query = "select * from institute";
		List<InstituteDTO> instituteDTOs = null;
		try {
			instituteDTOs = jdbcTemplate.query(query, new InstituteMapper());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return instituteDTOs;
	}
	
	//Student
	@PostMapping(value = "/addstudent")
	public String addstudent(@RequestBody StudentDTO studentDTO) {
		logger.info("add student method start");
		
		studentDTO.setStudentid(Integer.parseInt(userUtil.generateId()));
		int studentid = studentDTO.getStudentid();
		String studentname = studentDTO.getStudentname();
		Date studentdob=studentDTO.getStudentdob();
		String address = studentDTO.getAddress();
		String moblie=studentDTO.getMoblie();
		int age=studentDTO.getAge();
		
		String query = "insert into student ( STUDENTID, ADDRESS, AGE, MOBLIE, STUDENTDOB, STUDENTNAME  )  values (?, ?, ?, ?, ? , ?)";
		
		int temp = 0;

		try {
			temp = jdbcTemplate.update(query, studentid, address, age, moblie,studentdob, studentname);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
		}

		logger.info("add institute method end");

		return (temp == 1) ? "true" : "false";
	}

	@PostMapping(value = "/editstudent")
	public String editstudent(@RequestBody String userString) {
	
	logger.info("edit institute admin method start");

	JSONObject jsonObject = new JSONObject(userString);

	int studentid = jsonObject.getInt("studentid");
	String studentname = jsonObject.getString("studentname");
	String studentdob=jsonObject.getString("studentdob");
	String address = jsonObject.getString("address");
	String moblie=jsonObject.getString("moblie");
	String email=jsonObject.getString("email");
	
	String change = jsonObject.getString("change");
	
	int temp = 0;
	
	String query = new String();

	try {

		if (change.equals("studentname")) {
			query = "update student set studentname = ? where studentid = ?";
			temp = jdbcTemplate.update(query, studentname, studentid);
		} 
		else if (change.equals("address")) {
			query = "update institute set instituteaddress = ? where instituteid = ?";
			temp = jdbcTemplate.update(query,address, studentid);
		}
		else if (change.equals("moblie")) {
			query = "update student set moblie = ? where studentid = ?";
			temp = jdbcTemplate.update(query,moblie, studentid);
		}

		// if update in sql temp will be 1

	} catch (Exception e) {
		logger.error(e.getMessage());
		// if any error occurs
		return "false";
	}
	logger.info("edit course admin method end");
	return (temp == 1) ? "true" : "false";

	}

	@PostMapping(value = "/deletestudent")
	public String deletestudent(@RequestBody String jsonUser) {
		logger.info("delete institutee method start");
		JSONObject jsonObject = new JSONObject(jsonUser);
		int studentid = jsonObject.getInt("studentid");
		

		int temp = 0;
		try {
			String query = "delete from student where studentid = ? ";
			temp = jdbcTemplate.update(query, studentid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// if any error occurs
			return "false";
		}
		logger.info("delete student method end");
		return (temp == 1) ? "true" : "false";
	}

	@PostMapping(value = "/viewstudent")
	public List<StudentDTO> viewstudent() {

		String query = "select * from student";
		List<StudentDTO> studentDTOs = null;
		try {
			studentDTOs = jdbcTemplate.query(query, new StudentMapper());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return studentDTOs;
	}
	

	

}
