package com.elens.demo.entity;


import org.springframework.stereotype.Component;



@Component
public class Entertainment {

	
	long id;
	
	double streamingSubscriptions;
	
	double musicSubscriptions;
	
	double cinemaAndEvents;
	
	double shoppingBills;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getStreamingSubscriptions() {
		return streamingSubscriptions;
	}

	public void setStreamingSubscriptions(double streamingSubscriptions) {
		this.streamingSubscriptions = streamingSubscriptions;
	}

	public double getMusicSubscriptions() {
		return musicSubscriptions;
	}

	public void setMusicSubscriptions(double musicSubscriptions) {
		this.musicSubscriptions = musicSubscriptions;
	}

	public double getCinemaAndEvents() {
		return cinemaAndEvents;
	}

	public void setCinemaAndEvents(double cinemaAndEvents) {
		this.cinemaAndEvents = cinemaAndEvents;
	}

	public double getShoppingBills() {
		return shoppingBills;
	}

	public void setShoppingBills(double shoppingBills) {
		this.shoppingBills = shoppingBills;
	}

	public Entertainment() {
		super();
	}

	public Entertainment(long id, double streamingSubscriptions, double musicSubscriptions, double cinemaAndEvents,
			double shoppingBills) {
		super();
		this.id = id;
		this.streamingSubscriptions = streamingSubscriptions;
		this.musicSubscriptions = musicSubscriptions;
		this.cinemaAndEvents = cinemaAndEvents;
		this.shoppingBills = shoppingBills;
	}

	@Override
	public String toString() {
		return "Entertainment [id=" + id + ", streamingSubscriptions=" + streamingSubscriptions + ", musicSubscriptions="
				+ musicSubscriptions + ", cinemaAndEvents=" + cinemaAndEvents + ", shoppingBills=" + shoppingBills
				+ "]";
	}
	
}
