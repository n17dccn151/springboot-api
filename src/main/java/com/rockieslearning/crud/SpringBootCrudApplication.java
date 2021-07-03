package com.rockieslearning.crud;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;




@SpringBootApplication
public class SpringBootCrudApplication {


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
	}


//	@Bean
//	public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
//		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//		AuthFilter authFilter = new AuthFilter();
//		registrationBean.setFilter(authFilter);
//		registrationBean.addUrlPatterns("/api/orders/*", "/api/cart/*");
//		return registrationBean;
//	}


}
