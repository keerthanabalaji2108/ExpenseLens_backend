package com.elens.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.elens.demo.entity.Entertainment;
import com.elens.demo.entity.Food;
import com.elens.demo.entity.User;
import com.elens.demo.entity.Utility;
import com.elens.demo.repository.UserDAO;
import java.util.*;
@Service("UserService")
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public UserService() {
		super();
	}

	public UserService(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	public User addNewUser(User user) {
		User userSaved = userDAO.save(user);
		return userSaved;
	}

	public User getUserByUsernameAndPassword(String username, String pwd) {
		return userDAO.findByusernameAndPassword(username, pwd);
	}

	public boolean removeUserById(long id) {
		if (userDAO.findById(id) != null) {
			userDAO.deleteById(id);
			return true;
		} else {
			throw new RuntimeException("id not found");
		}

	}

	public void removeAllUsers() {
		userDAO.deleteAll();
	}

	public void updateEntertainment(long id, Entertainment entertainment) {
		User userSaved = userDAO.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
		userSaved.setEntertainment(userSaved.getEntertainment() + entertainment.getCinemaAndEvents()
				+ entertainment.getMusicSubscriptions() + entertainment.getShoppingBills()
				+ entertainment.getStreamingSubscriptions());
		
		userSaved.setTotalSpending(userSaved.getTotalSpending() + entertainment.getCinemaAndEvents()
		+ entertainment.getMusicSubscriptions() + entertainment.getShoppingBills()
		+ entertainment.getStreamingSubscriptions());
		
		userSaved.setTotalSaving(userSaved.getSalary()-userSaved.getTotalSpending());
		userDAO.save(userSaved);

	}
	
	public void updateFood(long id, Food food) {
		User userSaved = userDAO.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
		userSaved.setFood(userSaved.getFood() + food.getBeverage() + food.getDiningOut() + food.getGroceries() + food.getTakeOut());
		
		userSaved.setTotalSpending(userSaved.getTotalSpending() + food.getBeverage() + food.getDiningOut() + food.getGroceries() + food.getTakeOut());
		
		userSaved.setTotalSaving(userSaved.getSalary()-userSaved.getTotalSpending());
		userDAO.save(userSaved);
	}
	
	public void updateUtility(long id, Utility utility) {
		User userSaved = userDAO.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
		userSaved.setUtility(userSaved.getUtility() + utility.getElectricity() + utility.getGas() + utility.getMaintenance()
		+ utility.getMobile() + utility.getRent() + utility.getWater());
		
		userSaved.setTotalSpending(userSaved.getTotalSpending() + utility.getElectricity() + utility.getGas() + utility.getMaintenance()
		+ utility.getMobile() + utility.getRent() + utility.getWater());
		
		userSaved.setTotalSaving(userSaved.getSalary()-userSaved.getTotalSpending());
		userDAO.save(userSaved);
	}
	
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}
	
	public List<Double> getAsPercentage(long id) {
		User userSaved = userDAO.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
		List<Double> percentage = new ArrayList<>();
		percentage.add(Double.parseDouble(String.format("%.2f", (100*(userSaved.getEntertainment()/userSaved.getTotalSpending())))));
		percentage.add(Double.parseDouble(String.format("%.2f", (100*(userSaved.getFood()/userSaved.getTotalSpending())))));
		percentage.add(Double.parseDouble(String.format("%.2f", (100*(userSaved.getUtility()/userSaved.getTotalSpending())))));
		return percentage;

	}
	
	public List<Double> getSpendingVsSaving(@PathVariable long id) {
		User userSaved = userDAO.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
		List<Double> percentage = new ArrayList<>();
		percentage.add(Double.parseDouble(String.format("%.2f", (100*(userSaved.getTotalSpending()/userSaved.getSalary())))));
		percentage.add(Double.parseDouble(String.format("%.2f", (100*(userSaved.getTotalSaving()/userSaved.getSalary())))));
		return percentage;
	}
	

}
