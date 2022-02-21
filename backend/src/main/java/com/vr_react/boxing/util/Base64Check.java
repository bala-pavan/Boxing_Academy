package com.vr_react.boxing.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class Base64Check {

	public String encodeBase64(String s) {
		return java.util.Base64.getEncoder().encodeToString(s.getBytes());
	}

	public String decodeBase64(String s) {
		try {
			if (checkForEncode(s)) {
				return new String(java.util.Base64.getDecoder().decode(s));
			} else {
				return s;
			}
		} catch (Exception e) {
			return s;
		}
	}

	public Boolean checkForEncode(String string) {
		String pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(string);
		if (string.equals("test")) {
			return false;
		}
		else if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
}
