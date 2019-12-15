package com.team.alpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.alpha.model.User;
import com.team.alpha.service.UserService;

@RestController
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="/search")
	public ResponseEntity<User> findUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
		User user = userService.searchByUsernamePassword(username, password);
		if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);	
	}
}
