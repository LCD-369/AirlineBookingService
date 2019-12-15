package com.team.alpha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.UserDao;
import com.team.alpha.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User searchByUsernamePassword(String username, String password) {
		return userDao.findByUsernamePassword(username, password);
	}
}
