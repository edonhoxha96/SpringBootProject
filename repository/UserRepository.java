package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;  
import com.example.demo.model.UserRecord; 

public interface UserRepository extends CrudRepository<UserRecord, Integer>{
	Optional<UserRecord> findByUsername(String username);
	Boolean existsByUsername(String username);
}
