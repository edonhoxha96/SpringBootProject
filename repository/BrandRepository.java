package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Brand;

public interface BrandRepository extends CrudRepository<Brand, Integer>{
	public boolean existsByName(String name);
	Page<Brand> findByNameContainingIgnoreCase(String name, Pageable paging);
	Page<Brand> findAll(Pageable paging);
}
