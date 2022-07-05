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

import com.example.demo.model.Costumer;
import com.example.demo.service.CostumerService;

@RestController
public class CostumerController {
	@Autowired
	private CostumerService costumerService;
	
	@GetMapping("/costumers")
	public List<Costumer> getAllCostumers(){
		return costumerService.getAllCostumers();
	}
	
	@GetMapping("/costumers/{id}")
	public ResponseEntity<Costumer> findCostumer(@PathVariable int id) {
		Costumer costumer = costumerService.findOne(id);
		return new ResponseEntity<Costumer>(costumer, HttpStatus.OK);
	}
	
	@PostMapping("/add-costumer")
	public ResponseEntity<Costumer> addCostumer(@RequestBody Costumer costumer) {
		Costumer c = costumerService.addCostumer(costumer);
		return new ResponseEntity<Costumer>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-costumer")
	public ResponseEntity<Costumer> updateCostumer(@RequestBody Costumer costumer) {
		Costumer c = costumerService.updateCostumer(costumer);
		return new ResponseEntity<Costumer>(c, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/costumers/delete/{id}")
	public ResponseEntity<Costumer> deleteCostumer(@PathVariable int id) {
		costumerService.deleteCostumer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
