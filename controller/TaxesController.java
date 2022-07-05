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

import com.example.demo.model.Taxes;
import com.example.demo.service.TaxesService;

@RestController
public class TaxesController {
	@Autowired
	private TaxesService taxesService;
	
	@GetMapping("/taxes")
	public List<Taxes> getAllTaxes(){
		return taxesService.getAllTaxes();
	}
	
	@GetMapping("/taxes/{id}")
	public ResponseEntity<Taxes> findTax(@PathVariable int id) {
		Taxes t = taxesService.findOne(id);
		return new ResponseEntity<Taxes>(t, HttpStatus.OK);
	}
	
	@PostMapping("/add-taxes")
	public ResponseEntity<Taxes> addTaxes(@RequestBody Taxes tax) {
		Taxes t = taxesService.addTaxes(tax);
		return new ResponseEntity<Taxes>(t, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-taxes")
	public ResponseEntity<Taxes> updateTaxes(@RequestBody Taxes tax) {
		Taxes t = taxesService.updateTaxes(tax);
		return new ResponseEntity<Taxes>(t, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/taxes/delete/{id}")
	public ResponseEntity<Taxes> deleteTaxes(@PathVariable int id) {
		taxesService.deleteTaxes(id);
		return new ResponseEntity<Taxes>(HttpStatus.OK);
	}
}
