package com.vr_react.boxing;

import javax.servlet.Servlet;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BoxingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoxingApplication.class, args);
		
	}
//	@Bean
//	public ServletRegistrationBean h2servletRegistration() {
//	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebdavServlet());
//	    registration.addUrlMappings("/h2/*");
//	    return registration;
//	}
}
