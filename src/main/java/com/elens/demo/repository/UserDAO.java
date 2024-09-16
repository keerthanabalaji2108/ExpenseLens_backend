package com.elens.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.elens.demo.entity.User;

@Repository("UserDAO")
public class UserDAO {
	List<User> list = new ArrayList<User>();

	long id = 1;
	public UserDAO()
	{
		list.add(new User(1,"keerthana", "Keerthana B", "kiki02", 50000, 0, 50000, 0, 0, 0));
		id++;
	}

	public User saveNew (User user)
	{
		for(User u : list)
		{
			if(user.getUsername().equals(u.getUsername()))
			{
				return null;
			}
		}
		user.setEntertainment(0);
		user.setFood(0);
		user.setUtility(0);
		user.setId(id);
		user.setTotalSpending(0);
		user.setTotalSaving(0);
		id++;
		list.add(user);
		return user;
	}
	
	public User findByusernameAndPassword(String username, String pwd)
	{
		for(User u: list)
		{
			if((username.equals(u.getUsername())) && (pwd.equals(u.getPassword())))
			{
				return u;
			}
		}
		return null;
	}

	public User findById(long ID)
	{
		for(User u: list)
		{
			if(ID == u.getId())
			{
				return u;
			}
		}
		return null;
	}
	
	public User save(User u)
	{
		for(User user: list)
		{
			if(user.getId() == u.getId())
			{
				user.setEntertainment(u.getEntertainment());
				user.setFood(u.getFood());
				user.setName(u.getName());
				user.setPassword(u.getPassword());
				user.setSalary(u.getSalary());
				user.setTotalSaving(u.getTotalSaving());
				user.setTotalSpending(u.getTotalSpending());
				user.setUsername(u.getUsername());
				user.setUtility(u.getUtility());
				return user;
			}
		}
		return null;
	}
	
	public List<User> findAll() {
		return list;
	}
	
}
