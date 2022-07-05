package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.SalesOrder;

public interface SalesOrderRepository extends CrudRepository<SalesOrder, Integer>{

}
