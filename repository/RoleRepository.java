package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{
	Optional<Role> findByName(String name);
}
