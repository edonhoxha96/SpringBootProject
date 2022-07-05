package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Properties;

public interface PropertiesRepository extends CrudRepository<Properties, Integer>{
	boolean existsByName(String name);
}
