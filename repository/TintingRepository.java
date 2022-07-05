package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;  
import com.example.demo.model.Tinting; 

public interface TintingRepository extends CrudRepository<Tinting, Integer>{
	boolean existsByName(String name);
}
