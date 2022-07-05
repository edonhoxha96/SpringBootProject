package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Sector;

public interface SectorRepository extends CrudRepository<Sector, Integer>{
	boolean existsByName(String name);
}
