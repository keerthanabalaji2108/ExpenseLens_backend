package com.elens.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elens.demo.entity.Entertainment;

@Repository("EntertainmentDAO")
public interface EntertainmentDAO extends JpaRepository <Entertainment, Long>{

}
