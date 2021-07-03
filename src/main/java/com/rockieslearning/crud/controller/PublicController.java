package com.rockieslearning.crud.controller;


import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class PublicController {


	@Autowired
	UserRepository userRepository;
	@RequestMapping({ "/a" })
	public List<User> all() {
		return userRepository.findAll();
	}

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "hello";
	}

}