package com.elens.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.elens.demo.entity.Food;

@Repository("FoodDAO")
public class FoodDAO {
	List<Food> list = new ArrayList<Food>();

	long id = 1;
	public FoodDAO()
	{
		list.add(new Food(1, 0, 0, 0, 0));
		id++;
	}
	
	public Food findById(long ID)
	{
		for(Food f : list)
		{
			if(ID == f.getId())
			{
				return f;
			}
		}
		return null;
	}
	
	public Food save(Food f)
	{
		for(Food food: list)
		{
			if(food.getId() == f.getId())
			{
				food.setBeverage(f.getBeverage());
				food.setDiningOut(f.getDiningOut());
				food.setGroceries(f.getGroceries());
				food.setTakeOut(f.getTakeOut());
				return food;
			}
		}
		return null;
	}
	
	public Food saveNew (Food food)
	{
		food.setBeverage(0);
		food.setDiningOut(0);
		food.setGroceries(0);
		food.setTakeOut(0);
		food.setId(id);
		id++;
		list.add(food);
		return food;
	}

}
