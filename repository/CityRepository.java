package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.City;

public interface CityRepository extends CrudRepository<City, Integer> {
	public boolean existsByNameAndCountryId(String name, int countryId);
}
