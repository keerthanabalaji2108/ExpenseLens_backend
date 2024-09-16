package com.elens.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.elens.demo.entity.Entertainment;

@Repository("EntertainmentDAO")
public class EntertainmentDAO {
	List<Entertainment> list = new ArrayList<Entertainment>();

	long id = 1;
	public EntertainmentDAO()
	{
		list.add(new Entertainment(1, 0, 0, 0, 0));
		id++;
	}
	
	public Entertainment findById(long ID)
	{
		for(Entertainment e : list)
		{
			if(ID == e.getId())
			{
				return e;
			}
		}
		return null;
	}
	
	public Entertainment save(Entertainment e)
	{
		for(Entertainment ent: list)
		{
			if(ent.getId() == e.getId())
			{
				ent.setCinemaAndEvents(e.getCinemaAndEvents());
				ent.setMusicSubscriptions(e.getMusicSubscriptions());
				ent.setShoppingBills(e.getShoppingBills());
				ent.setStreamingSubscriptions(e.getStreamingSubscriptions());
				return ent;
			}
		}
		return null;
	}
	
	public Entertainment saveNew (Entertainment ent)
	{
		ent.setCinemaAndEvents(0);
		ent.setMusicSubscriptions(0);
		ent.setShoppingBills(0);
		ent.setStreamingSubscriptions(0);
		ent.setId(id);
		id++;
		list.add(ent);
		return ent;
	}

}
