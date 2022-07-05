package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Sales2;

public interface Sales2Repository extends JpaRepository<Sales2, Integer>{
	Page<Sales2> findAll(Pageable paging);
}
