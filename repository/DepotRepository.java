package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Depot;

public interface DepotRepository extends CrudRepository<Depot, Integer>{
	public boolean existsByName(String name);
}
