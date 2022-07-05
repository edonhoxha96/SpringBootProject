package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Status;

public interface StatusRepository extends CrudRepository<Status, Integer>{
	boolean existsByName(String name);
}
