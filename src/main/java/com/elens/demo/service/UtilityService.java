package com.elens.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elens.demo.entity.Utility;
import com.elens.demo.repository.UtilityDAO;

@Service("UtilityService")
public class UtilityService {

	@Autowired
	private UtilityDAO utilityDAO;

	public UtilityService(UtilityDAO utilityDAO) {
		super();
		this.utilityDAO = utilityDAO;
	}

	public UtilityService() {
		super();
	}

	public Utility updateUtility(long id, Utility utility) {
		Utility existingUtility = utilityDAO.findById(id);
		if(existingUtility == null)
		{
			throw new RuntimeException("id not found");
		}

		existingUtility.setElectricity(existingUtility.getElectricity() + utility.getElectricity());
		existingUtility.setWater(existingUtility.getWater() + utility.getWater());
		existingUtility.setRent(existingUtility.getRent() + utility.getRent());
		existingUtility.setGas(existingUtility.getGas() + utility.getGas());
		existingUtility.setMobile(existingUtility.getMobile() + utility.getMobile());
		existingUtility.setMaintenance(existingUtility.getMaintenance() + utility.getMaintenance());

		// Save and return the updated entity
		return utilityDAO.save(existingUtility);
	}

	public void save(Utility utility) {
		utilityDAO.saveNew(utility);
	}
	
	public Utility getUtilityById(long id) {
		Utility existingUtility =  utilityDAO.findById(id);
		if(existingUtility == null)
		{
			throw new RuntimeException("id not found");
		}
		else
		{
			return existingUtility;
		}
	}
	
//	public void deleteById(long id)
//	{
//		utilityDAO.deleteById(id);
//	}
//	
//	public void deleteAll()
//	{
//		utilityDAO.deleteAll();
//	}


}
