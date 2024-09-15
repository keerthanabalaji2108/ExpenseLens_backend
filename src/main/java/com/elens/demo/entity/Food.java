package com.elens.demo.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Component
@Entity
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq_gen")
    @SequenceGenerator(name = "food_seq_gen", sequenceName = "food_seq", allocationSize = 1)
	@Column(name = "id")
	long id;
	
	@Column
	double groceries;
	
	@Column
	double diningOut;
	
	@Column
	double beverage;
	
	@Column
	double takeOut;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getGroceries() {
		return groceries;
	}

	public void setGroceries(double groceries) {
		this.groceries = groceries;
	}

	public double getDiningOut() {
		return diningOut;
	}

	public void setDiningOut(double diningOut) {
		this.diningOut = diningOut;
	}

	public double getBeverage() {
		return beverage;
	}

	public void setBeverage(double beverage) {
		this.beverage = beverage;
	}

	public double getTakeOut() {
		return takeOut;
	}

	public void setTakeOut(double takeOut) {
		this.takeOut = takeOut;
	}

	public Food() {
		super();
	}

	public Food(long id, double groceries, double diningOut, double beverage, double takeOut) {
		super();
		this.id = id;
		this.groceries = groceries;
		this.diningOut = diningOut;
		this.beverage = beverage;
		this.takeOut = takeOut;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", groceries=" + groceries + ", diningOut=" + diningOut + ", beverage=" + beverage
				+ ", takeOut=" + takeOut + "]";
	}
	

}
