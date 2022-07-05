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

import com.example.demo.model.PurchasesOrder;
import com.example.demo.service.PurchasesOrderService;

@RestController
public class PurchasesOrderController {
	@Autowired
	private PurchasesOrderService puirchasesOrderService;
	
	@GetMapping("/purchasesOrders")
	public List<PurchasesOrder> getAllPurchasesOrders(){
		return puirchasesOrderService.getAllPurchasesOrders();
	}
	
	@GetMapping("/purchasesOrders/{id}")
	public ResponseEntity<PurchasesOrder> findPurchasesOrder(@PathVariable int id) {
		PurchasesOrder po = puirchasesOrderService.findOne(id);
		return new ResponseEntity<PurchasesOrder>(po, HttpStatus.OK);
	}
	
	@PostMapping("/add-purchasesOrder")
	public ResponseEntity<PurchasesOrder> addPurchasesOrder(@RequestBody PurchasesOrder puirchasesOrder) {
		PurchasesOrder po = puirchasesOrderService.addPurchasesOrder(puirchasesOrder);
		return new ResponseEntity<PurchasesOrder>(po, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-purchasesOrder")
	public ResponseEntity<PurchasesOrder> updatePurchasesOrder(@RequestBody PurchasesOrder puirchasesOrder) {
		PurchasesOrder po = puirchasesOrderService.updatePurchasesOrder(puirchasesOrder);
		return new ResponseEntity<PurchasesOrder>(po, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/purchasesOrders/delete/{id}")
	public ResponseEntity<PurchasesOrder> deletePurchasesOrder(@PathVariable int id) {
		puirchasesOrderService.deletePurchasesOrder(id);
		return new ResponseEntity<PurchasesOrder>(HttpStatus.OK);
	}
}
