package com.elens.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.elens.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("UserDAO")
public interface UserDAO extends JpaRepository <User, Long>{

	
	public User findByUsernameAndPassword(String username, String pwd);

}
