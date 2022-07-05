package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Stock;


public interface StockRepository extends JpaRepository<Stock, Integer>{
	Page<Stock> findBySectorId(int sectorId, Pageable pageable);
	Page<Stock> findAll(Pageable paging);
	List<Stock> findByProductSupplierId(int id);
}
