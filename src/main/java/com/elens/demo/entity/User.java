package com.elens.demo.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "\"user\"")
public class User {
	
	@Id
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
	long id;

	@Column (unique = true)
	String username;
	
	@Column
	String name;
	
	@Column
	String password;

	@Column
	double salary;

	@Column
	double totalSpending;

	@Column
	double totalSaving;

	@Column
	double utility;

	@Column
	double entertainment;

	@Column
	double food;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getTotalSpending() {
		return totalSpending;
	}

	public void setTotalSpending(double totalSpending) {
		this.totalSpending = totalSpending;
	}

	public double getTotalSaving() {
		return totalSaving;
	}

	public void setTotalSaving(double totalSaving) {
		this.totalSaving = totalSaving;
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}

	public double getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(double entertainment) {
		this.entertainment = entertainment;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public User() {
		super();
	}

	public User(long id, String username, String name, String password, double salary, double totalSpending, double totalSaving, double utility,
			double entertainment, double food) {
		super();
		this.id = id;
		this.username = username;
		this.name= name;
		this.password = password;
		this.salary = salary;
		this.totalSpending = totalSpending;
		this.totalSaving = totalSaving;
		this.utility = utility;
		this.entertainment = entertainment;
		this.food = food;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password + ", salary="
				+ salary + ", totalSpending=" + totalSpending + ", totalSaving=" + totalSaving + ", utility=" + utility
				+ ", entertainment=" + entertainment + ", food=" + food + "]";
	}



}
