package com.elens.demo.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Component
@Entity
@Table(name = "utility")
public class Utility {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entertainment_seq_gen")
    @SequenceGenerator(name = "entertainment_seq_gen", sequenceName = "entertainment_seq", allocationSize = 1)
	@Column(name = "id")
	long id;
	
	@Column
	double electricity;
	
	@Column
	double water;
	
	@Column
	double rent;
	
	@Column
	double gas;
	
	@Column
	double mobile;
	
	@Column
	double maintenance;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getElectricity() {
		return electricity;
	}
	public void setElectricity(double electricity) {
		this.electricity = electricity;
	}
	public double getWater() {
		return water;
	}
	public void setWater(double water) {
		this.water = water;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public double getGas() {
		return gas;
	}
	public void setGas(double gas) {
		this.gas = gas;
	}
	public double getMobile() {
		return mobile;
	}
	public void setMobile(double mobile) {
		this.mobile = mobile;
	}
	public double getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(double maintenance) {
		this.maintenance = maintenance;
	}
	public Utility() {
		super();
	}
	public Utility(long id, double electricity, double water, double rent, double gas, double mobile,
			double maintenance) {
		super();
		this.id = id;
		this.electricity = electricity;
		this.water = water;
		this.rent = rent;
		this.gas = gas;
		this.mobile = mobile;
		this.maintenance = maintenance;
	}
	@Override
	public String toString() {
		return "Utility [id=" + id + ", electricity=" + electricity + ", water=" + water + ", rent=" + rent
				+ ", gas=" + gas + ", mobile=" + mobile + ", maintenance=" + maintenance + "]";
	}
	
	

}
