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

import com.example.demo.model.ProductionYear;
import com.example.demo.service.ProductionYearService;

@RestController
public class ProductionYearController {
	@Autowired
	private ProductionYearService productionYearService;
	
	@GetMapping("/production-years")
	public List<ProductionYear> getAllProductionYears(){
		return productionYearService.getAllProductionYears();
	}
	
	@GetMapping("/production-years/{id}")
	public ResponseEntity<ProductionYear> findProductionYear(@PathVariable int id) {
		ProductionYear productionYear = productionYearService.findOne(id);
		return new ResponseEntity<>(productionYear, HttpStatus.OK);
	}
	
	@PostMapping("/add-production-year")
	public ResponseEntity<ProductionYear> addProductionYear(@RequestBody ProductionYear productionYear) {
		ProductionYear p = productionYearService.addProductionYear(productionYear);
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-production-year")
	public ResponseEntity<ProductionYear> updateProductionYear(@RequestBody ProductionYear productionYear) {
		ProductionYear p = productionYearService.updateProductionYear(productionYear);
		return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/production-years/delete/{id}")
	public ResponseEntity<ProductionYear> deleteProductionYear(@PathVariable int id) {
		productionYearService.deleteProductionYear(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
