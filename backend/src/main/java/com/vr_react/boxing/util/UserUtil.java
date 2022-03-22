package com.vr_react.boxing.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {
	
	public boolean isProperEmail(String email){
		
		if(email.contains("@") && email.contains(".com")) {
			return true;
		}
		return false;
	}
	
	public String createId(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy");
		return "ID"+dateFormat.format(new Date())+generateUserName();
	}
	
	public String generateUserName() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 6;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;
		
	}

	public String generateId() {
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    return String.format("%06d", number);
		
	}
}
