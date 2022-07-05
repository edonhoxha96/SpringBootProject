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

import com.example.demo.model.Supplier;
import com.example.demo.service.SupplierService;

@RestController
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/suppliers")
	public List<Supplier> getAllSuppliers(){
		return supplierService.getAllSuppliers();
	}
	
	@GetMapping("/suppliers/{id}")
	public ResponseEntity<Supplier> findSupplier(@PathVariable int id) {
		Supplier supplier = supplierService.findOne(id);
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
	}
	
	@PostMapping("/add-supplier")
	public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
		Supplier s = supplierService.addSupplier(supplier);
		return new ResponseEntity<Supplier>(s, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-supplier")
	public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier) {
		Supplier s = supplierService.updateSupplier(supplier);
		return new ResponseEntity<Supplier>(s, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/suppliers/delete/{id}")
	public ResponseEntity<Supplier> deleteSupplier(@PathVariable int id) {
		supplierService.deleteSupplier(id);
		return new ResponseEntity<Supplier>(HttpStatus.OK);
	}
}
