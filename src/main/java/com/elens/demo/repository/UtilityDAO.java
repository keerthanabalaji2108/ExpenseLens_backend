package com.elens.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elens.demo.entity.Utility;

@Repository("UtilityDAO")
public interface UtilityDAO extends JpaRepository <Utility, Long>{

}
