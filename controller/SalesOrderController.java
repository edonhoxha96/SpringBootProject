package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SalesOrder;
import com.example.demo.service.SalesOrderService;

@RestController
public class SalesOrderController {
	@Autowired
	private SalesOrderService salesOrderService;
	
	@GetMapping("/salesOrders")
	public List<SalesOrder> getAllSalesOrders(){
		return salesOrderService.getAllSalesOrders();
	}
	
	@GetMapping("/salesOrders/{id}")
	public ResponseEntity<SalesOrder> findSalesOrder(@PathVariable int id) {
		SalesOrder salesOrder = salesOrderService.findOne(id);
		return new ResponseEntity<SalesOrder>(salesOrder, HttpStatus.OK);
	}
	
	@PostMapping("/add-salesOrder")
	public ResponseEntity<SalesOrder> addSalesOrder(@RequestBody SalesOrder salesOrder) {
		SalesOrder so = salesOrderService.addSalesOrder(salesOrder);
		return new ResponseEntity<SalesOrder>(so, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-salesOrder")
	public ResponseEntity<SalesOrder> updateSalesOrder(@RequestBody SalesOrder salesOrder) {
		SalesOrder so = salesOrderService.updateSalesOrder(salesOrder);
		return new ResponseEntity<SalesOrder>(so, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/salesOrders/delete/{id}")
	public ResponseEntity<SalesOrder> deleteSalesOrder(@PathVariable int id) {
		salesOrderService.deleteSalesOrder(id);
		return new ResponseEntity<SalesOrder>(HttpStatus.OK);
	}
	
	@DeleteMapping("/salesOrders/cancel/{id}")
	public ResponseEntity<SalesOrder> cancelSalesOrder(@PathVariable int id) {
		salesOrderService.cancelSalesOrder(id);
		return new ResponseEntity<SalesOrder>(HttpStatus.OK);
	}
}
