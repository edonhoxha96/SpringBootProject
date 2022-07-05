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

import com.example.demo.model.Unit;
import com.example.demo.service.UnitService;

@RestController
public class UnitController {
	@Autowired
	private UnitService unitService;
	
	@GetMapping("/units")
	public List<Unit> getAllUnits(){
		return unitService.getAllUnits();
	}
	
	@GetMapping("/units/{id}")
	public ResponseEntity<Unit> findUnit(@PathVariable int id) {
		Unit unit = unitService.findOne(id);
		return new ResponseEntity<Unit>(unit, HttpStatus.OK);
	}
	
	@PostMapping("/add-unit")
	public ResponseEntity<Unit> addUnit(@RequestBody Unit unit) {
		Unit u = unitService.addUnit(unit);
		return new ResponseEntity<Unit>(u, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-unit")
	public ResponseEntity<Unit> updateUnit(@RequestBody Unit unit) {
		Unit u = unitService.updateUnit(unit);
		return new ResponseEntity<Unit>(u, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/units/delete/{id}")
	public ResponseEntity<Unit> deleteUnit(@PathVariable int id) {
		unitService.deleteUnit(id);
		return new ResponseEntity<Unit>(HttpStatus.OK);
	}
}
