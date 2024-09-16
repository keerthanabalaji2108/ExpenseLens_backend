package com.elens.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elens.demo.entity.Entertainment;
import com.elens.demo.repository.EntertainmentDAO;

@Service("EntertainmentService")
public class EntertainmentService {

	@Autowired
	EntertainmentDAO entertainmentDAO;

	public EntertainmentService() {
		super();
	}

	public EntertainmentService(EntertainmentDAO entertainmentDAO) {
		super();
		this.entertainmentDAO = entertainmentDAO;
	}

	public Entertainment updateEntertainment(long id, Entertainment entertainment) {
		Entertainment existingEntertainment = entertainmentDAO.findById(id);
		if(existingEntertainment == null)
		{
			throw new RuntimeException("id not found");
		}

		existingEntertainment.setStreamingSubscriptions(
				existingEntertainment.getStreamingSubscriptions() + entertainment.getStreamingSubscriptions());
		existingEntertainment.setMusicSubscriptions(
				existingEntertainment.getMusicSubscriptions() + entertainment.getMusicSubscriptions());
		existingEntertainment
				.setCinemaAndEvents(existingEntertainment.getCinemaAndEvents() + entertainment.getCinemaAndEvents());
		existingEntertainment
				.setShoppingBills(existingEntertainment.getShoppingBills() + entertainment.getShoppingBills());

		// Save and return the updated entity
		return entertainmentDAO.save(existingEntertainment);
	}
	
	public void save(Entertainment entertainment)
	{
		entertainmentDAO.saveNew(entertainment);
	}
	
	public Entertainment getEntertainmentById(long id) {
		Entertainment existingEntertainment =  entertainmentDAO.findById(id);
		if(existingEntertainment == null)
		{
			throw new RuntimeException("id not found");
		}
		else
		{
			return existingEntertainment;
		}
	}
	
//	public void deleteById(long id)
//	{
//		entertainmentDAO.deleteById(id);
//	}
//	
//	public void deleteAll()
//	{
//		entertainmentDAO.deleteAll();
//	}


}
