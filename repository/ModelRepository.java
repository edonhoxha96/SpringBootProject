package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Model;

public interface ModelRepository extends CrudRepository<Model, Integer>{
	public boolean existsByName(String name);
	Page<Model> findByNameContainingIgnoreCase(String name, Pageable paging);
	Page<Model> findAll(Pageable paging);
}
