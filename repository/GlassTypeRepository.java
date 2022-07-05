package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.GlassType;

public interface GlassTypeRepository extends CrudRepository<GlassType, Integer>{
	public boolean existsByName(String name);
}
