package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Purchases;

public interface PurchasesRepository extends CrudRepository<Purchases, Integer>{

}
