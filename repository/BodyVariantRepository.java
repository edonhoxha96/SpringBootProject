package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.BodyVariant;

public interface BodyVariantRepository extends CrudRepository<BodyVariant, Integer> {
	public boolean existsByName(String name);
}
