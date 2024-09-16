package com.elens.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elens.demo.entity.Food;
import com.elens.demo.repository.FoodDAO;

@Service("FoodService")
public class FoodService {

	@Autowired
	private FoodDAO foodDAO;

	public FoodService(FoodDAO foodDAO) {
		super();
		this.foodDAO = foodDAO;
	}

	public FoodService() {
		super();
	}

	public Food updateFood(long id, Food food) {
		Food existingFood = foodDAO.findById(id);
		if(existingFood == null)
		{
			throw new RuntimeException("id not found");
		}

		existingFood.setGroceries(existingFood.getGroceries() + food.getGroceries());
		existingFood.setDiningOut(existingFood.getDiningOut() + food.getDiningOut());
		existingFood.setBeverage(existingFood.getBeverage() + food.getBeverage());
		existingFood.setTakeOut(existingFood.getTakeOut() + food.getTakeOut());

		// Save and return the updated entity
		return foodDAO.save(existingFood);
	}

	public void save(Food food) {
		foodDAO.saveNew(food);
	}
	
	public Food getFoodById(long id) {
		Food existingFood = foodDAO.findById(id);
		if(existingFood == null)
		{
			throw new RuntimeException("id not found");
		}
		else
		{
			return existingFood;
		}
	}
	
//	public void deleteById(long id)
//	{
//		foodDAO.deleteById(id);
//	}
//	
//	public void deleteAll()
//	{
//		foodDAO.deleteAll();
//	}

}
