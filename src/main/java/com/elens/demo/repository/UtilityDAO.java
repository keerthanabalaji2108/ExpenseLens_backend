package com.elens.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.elens.demo.entity.Utility;

@Repository("UtilityDAO")
public class UtilityDAO {
	List<Utility> list = new ArrayList<Utility>();

	long id = 1;
	public UtilityDAO()
	{
		list.add(new Utility(1, 0, 0, 0, 0, 0, 0));
		id++;
	}
	
	public Utility findById(long ID)
	{
		for(Utility u : list)
		{
			if(ID == u.getId())
			{
				return u;
			}
		}
		return null;
	}
	
	public Utility save(Utility u)
	{
		for(Utility utility: list)
		{
			if(utility.getId() == u.getId())
			{
				utility.setElectricity(u.getElectricity());
				utility.setGas(u.getGas());
				utility.setMaintenance(u.getMaintenance());
				utility.setMobile(u.getMobile());
				utility.setRent(u.getRent());
				utility.setWater(u.getWater());
				return utility;
			}
		}
		return null;
	}
	
	public Utility saveNew (Utility utility)
	{
		utility.setElectricity(0);
		utility.setGas(0);
		utility.setMaintenance(0);
		utility.setMobile(0);
		utility.setRent(0);
		utility.setWater(0);
		utility.setId(id);
		id++;
		list.add(utility);
		return utility;
	}

}
