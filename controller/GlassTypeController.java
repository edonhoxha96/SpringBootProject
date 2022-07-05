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

import com.example.demo.service.GlassTypeService;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.GlassType;

@RestController
public class GlassTypeController {
	@Autowired
	private GlassTypeService glassTypeService;
	
	@GetMapping("/glass-types")
	public List<GlassType> getAllGlassTypes(){
		return glassTypeService.getAllGlassTypes();
	}
	
	@GetMapping("/glass-types/{id}")
	public ResponseEntity<GlassType> findGlassType(@PathVariable int id) {
		GlassType glassType = glassTypeService.findOne(id);
		return new ResponseEntity<GlassType>(glassType, HttpStatus.OK);
	}
	
	@PostMapping("/add-glass-type")
	public ResponseEntity<GlassType> addGlassType(@RequestBody GlassType glassType) throws BadRequestException, AlreadyExistsException {
		GlassType g = glassTypeService.addGlassType(glassType);
		return new ResponseEntity<GlassType>(g, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-glass-type")
	public ResponseEntity<GlassType> updateGlassType(@RequestBody GlassType glassType) throws BadRequestException {
		GlassType g = glassTypeService.updateGlassType(glassType);
		return new ResponseEntity<GlassType>(g, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/glass-types/delete/{id}")
	public ResponseEntity<GlassType> deleteGlassType(@PathVariable int id) {
		glassTypeService.deleteGlassType(id);
		return new ResponseEntity<GlassType>(HttpStatus.OK);
	}
}
