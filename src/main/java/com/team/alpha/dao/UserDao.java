package com.team.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	@Query(value="from User where username=:username")
	User findByUsername(@Param("username") String username);
	
	@Query(value="from User where username=:username and password=:password")
	User findByUsernamePassword(@Param("username") String username, @Param("password") String password);
	
}
