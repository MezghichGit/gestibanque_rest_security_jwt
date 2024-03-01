package com.sip.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.User;
import com.sip.ams.services.UserDetailsServiceImpl;

@RestController
@RequestMapping({ "/users" })
@CrossOrigin("*")
public class UserRestController {
	
	@Autowired
	private UserDetailsServiceImpl userService;

	@GetMapping
	public List<User> findAll() {
		return (List<User>) userService.getAllUsers();
	}
	
	@RequestMapping({ "/agents" })
	@GetMapping
	public List<User> findAllAgents() {
		return (List<User>) userService.getAllAgents();
	}


}
