package com.elens.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elens.demo.entity.*;
import com.elens.demo.service.*;

@RestController
@CrossOrigin(origins = { "http://myreactappbucket21082002.s3-website-eu-west-1.amazonaws.com/", "http://localhost:3003" })
//@CrossOrigin(origins = "http://54.90.184.214:3003")
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	private UserService userService;
	@Autowired
	private EntertainmentService entertainmentService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private UtilityService utilityService;

	// Add new user
	@PostMapping("/newUser")
	public ResponseEntity<User> addNewUser(@RequestBody User user) {
		user.setTotalSaving(user.getSalary());
		User userAdded = Optional.ofNullable(userService.addNewUser(user))
				.orElseThrow(() -> new RuntimeException("Failed to add user"));

		Entertainment entertainment = new Entertainment();
//		entertainment.setId(userSaved.getId());
		entertainmentService.save(entertainment);

		Food food = new Food();
		foodService.save(food);

		Utility utility = new Utility();
		utilityService.save(utility);

		return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
	}

	// Update entertainment cost
	@PutMapping("/updateEntertainment")
	public Entertainment updateEntertainment(@RequestParam("id") long id, @RequestBody Entertainment entertainment) {
		Entertainment entertainmentSaved = entertainmentService.updateEntertainment(id, entertainment);
		userService.updateEntertainment(id, entertainment);
		return entertainmentSaved;
	}

	// Update Food cost

	@PutMapping("/updateFood")
	public Food updateFood(@RequestParam("id") long id, @RequestBody Food food) {
		Food foodSaved = foodService.updateFood(id, food);
		userService.updateFood(id, food);
		return foodSaved;
	}

	// Update Utility cost

	@PutMapping("/updateUtility")
	public Utility updateUtility(@RequestParam("id") long id, @RequestBody Utility utility) {
		Utility utilitySaved = utilityService.updateUtility(id, utility);
		userService.updateUtility(id, utility);
		return utilitySaved;

	}

	// Login by user id and password

	@PostMapping("/getUser")
	public User getUserByUsernameAndPassword(@RequestBody Map<String, String> credentials) {
		String username = credentials.get("username");
		String pwd = credentials.get("password");
		User user = Optional.ofNullable(userService.getUserByUsernameAndPassword(username, pwd))
				.orElseThrow(() -> new RuntimeException("Invalid username or password"));
		return user;
	}

	// Get Percentage view of spending

	@GetMapping("/getAsPercentage/{id}")
	public List<Double> getAsPercentage(@PathVariable long id) {
		return userService.getAsPercentage(id);
	}

	@GetMapping("/getSpendingVsSaving/{id}")
	public List<Double> getSpendingVsSaving(@PathVariable long id) {
		return userService.getSpendingVsSaving(id);
	}

	// Get entertainment cost distribution

	@GetMapping("/getEntertainment/{id}")
	public Entertainment getEntertainmentById(@PathVariable long id) {
		return entertainmentService.getEntertainmentById(id);
	}

	// Get food cost distribution

	@GetMapping("/getFood/{id}")
	public Food getFoodById(@PathVariable long id) {
		return foodService.getFoodById(id);
	}

	// Get utility cost distribution

	@GetMapping("/getUtility/{id}")
	public Utility getUtilityById(@PathVariable long id) {
		return utilityService.getUtilityById(id);
	}

	// Display all users
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// Unsubscribed by user

//	@DeleteMapping("/removeUser/{id}")
//	public String removeUserById(@PathVariable long id) {
//		boolean msg = userService.removeUserById(id);
//		if (msg) {
//			entertainmentService.deleteById(id);
//			utilityService.deleteById(id);
//			foodService.deleteById(id);
//			return "Successfully removed";
//		}
//		return "Failed to remove";
//	}
//
//	// Deleting all users
//
//	@DeleteMapping("/removeAllUsers")
//	public String removeAllUsers() {
//		entertainmentService.deleteAll();
//		utilityService.deleteAll();
//		foodService.deleteAll();
//		userService.removeAllUsers();
//		return "Deleted all entries";
//	}

}