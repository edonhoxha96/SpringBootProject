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

import com.example.demo.model.Purchases;
import com.example.demo.service.PurchasesService;

@RestController
public class PurchasesController {
	@Autowired
	private PurchasesService purchasesService;
	
	@GetMapping("/purchases")
	public List<Purchases> getAllPurchases(){
		return purchasesService.getAllPurchases();
	}
	
	@GetMapping("/purchases/{id}")
	public ResponseEntity<Purchases> findPurchases(@PathVariable int id) {
		Purchases p = purchasesService.findOne(id);
		return new ResponseEntity<Purchases>(p, HttpStatus.OK);
	}
	
	@PostMapping("/add-purchases")
	public ResponseEntity<Purchases> addPurchases(@RequestBody Purchases purchases) {
		Purchases p = purchasesService.addPurchases(purchases);
		return new ResponseEntity<Purchases>(p, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-purchases")
	public ResponseEntity<Purchases> updatePurchases(@RequestBody Purchases purchases) {
		Purchases p = purchasesService.updatePurchases(purchases);
		return new ResponseEntity<Purchases>(p, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/purchases/delete/{id}")
	public ResponseEntity<Purchases> deletePurchases(@PathVariable int id) {
		purchasesService.deletePurchases(id);
		return new ResponseEntity<Purchases>(HttpStatus.OK);
	}
}
