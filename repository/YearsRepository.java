package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Years;

public interface YearsRepository extends CrudRepository<Years, Integer>{
	boolean existsByName(String name);
	Page<Years> findByNameContainingIgnoreCase(String name, Pageable paging);
	Page<Years> findAll(Pageable paging);
}
