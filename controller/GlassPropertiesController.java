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

import com.example.demo.service.GlassPropertiesService;
import com.example.demo.model.GlassProperties;

@RestController
public class GlassPropertiesController {
	@Autowired
	private GlassPropertiesService glassPropertiesService;
	
	@GetMapping("/glass-properties")
	public List<GlassProperties> getAllGlassPropertiess(){
		return glassPropertiesService.getAllGlassPropertiess();
	}
	
	@GetMapping("/glass-properties/{id}")
	public ResponseEntity<GlassProperties> findGlassProperties(@PathVariable int id) {
		GlassProperties glassProperties = glassPropertiesService.findOne(id);
		return new ResponseEntity<GlassProperties>(glassProperties, HttpStatus.OK);
	}
	
	@GetMapping("/glass-properties-by-product/{id}")
	public ResponseEntity<List<GlassProperties>> findGlassPropertiesbyProduct(@PathVariable int id) {
		List<GlassProperties> glassProperties = glassPropertiesService.findGlassPropertiesbyProduct(id);
		return new ResponseEntity<List<GlassProperties>>(glassProperties, HttpStatus.OK);
	}
	
	@PostMapping("/add-glass-properties")
	public ResponseEntity<GlassProperties> addGlassProperties(@RequestBody GlassProperties glassProperties) {
		GlassProperties g = glassPropertiesService.addGlassProperties(glassProperties);
		return new ResponseEntity<GlassProperties>(g, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-glass-properties")
	public ResponseEntity<GlassProperties> updateGlassProperties(@RequestBody GlassProperties glassProperties) {
		GlassProperties g = glassPropertiesService.updateGlassProperties(glassProperties);
		return new ResponseEntity<GlassProperties>(g, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/glass-properties/delete/{id}")
	public ResponseEntity<GlassProperties> deleteGlassProperties(@PathVariable int id) {
		glassPropertiesService.deleteGlassProperties(id);
		return new ResponseEntity<GlassProperties>(HttpStatus.OK);
	}
}
