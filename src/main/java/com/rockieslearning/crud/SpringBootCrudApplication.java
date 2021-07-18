package com.rockieslearning.crud;

import com.rockieslearning.crud.service.LocalFilesStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;


@SpringBootApplication
public class SpringBootCrudApplication implements CommandLineRunner {

	@Resource
    LocalFilesStorageService storageService;

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
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
