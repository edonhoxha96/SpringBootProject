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

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Depot;
import com.example.demo.service.DepotService;

@RestController
public class DepotController {
	@Autowired
	private DepotService depotService;
	
	@GetMapping("/depots")
	public List<Depot> getAllDepots(){
		return depotService.getAllDepots();
	}
	
	@GetMapping("/depots/{id}")
	public ResponseEntity<Depot> findDepot(@PathVariable int id) {
		Depot depot = depotService.findOne(id);
		return new ResponseEntity<Depot>(depot, HttpStatus.OK);
	}
	
	@PostMapping("/add-depot")
	public ResponseEntity<Depot> addDepot(@RequestBody Depot depot) throws BadRequestException, AlreadyExistsException {
		Depot d = depotService.addDepot(depot);
		return new ResponseEntity<Depot>(d, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-depot")
	public ResponseEntity<Depot> updateDepot(@RequestBody Depot depot) throws BadRequestException {
		Depot d = depotService.updateDepot(depot);
		return new ResponseEntity<Depot>(d, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/depots/delete/{id}")
	public ResponseEntity<Depot> deleteDepot(@PathVariable int id) {
		depotService.deleteDepot(id);
		return new ResponseEntity<Depot>(HttpStatus.OK);
	}
}
