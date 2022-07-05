package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Product;
import com.example.demo.model.ProductSupplier;
import com.example.demo.model.Supplier;

public interface ProductSupplierRepository extends CrudRepository<ProductSupplier, Integer>{
	boolean existsByProductAndSupplier(Product product, Supplier supplier);
	Page<ProductSupplier> findByProductEurocode(String eurocode, Pageable pageable);
	Page<ProductSupplier> findByProductModel(String Model, Pageable pageable);
	Page<ProductSupplier> findAll(Pageable paging);
}
