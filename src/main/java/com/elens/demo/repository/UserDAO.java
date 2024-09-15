package com.elens.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elens.demo.entity.User;

@Repository("UserDAO")
public interface UserDAO extends JpaRepository <User, Long>{
	
	public User findByusernameAndPassword(String username, String pwd);

}
