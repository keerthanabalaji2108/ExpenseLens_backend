package com.elens.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elens.demo.entity.Food;

@Repository("FoodDAO")
public interface FoodDAO extends JpaRepository <Food, Long>{

}
