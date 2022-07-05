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
import com.example.demo.model.Properties;
import com.example.demo.service.PropertiesService;

@RestController
public class PropertiesController {
	@Autowired
	private PropertiesService propertiesService;
	
	@GetMapping("/properties")
	public List<Properties> getAllPropertiess(){
		return propertiesService.getAllPropertiess();
	}
	
	@GetMapping("/properties/{id}")
	public ResponseEntity<Properties> findProperties(@PathVariable int id) {
		Properties properties = propertiesService.findOne(id);
		return new ResponseEntity<Properties>(properties, HttpStatus.OK);
	}
	
	@PostMapping("/add-properties")
	public ResponseEntity<Properties> addProperties(@RequestBody Properties properties) throws BadRequestException, AlreadyExistsException {
		Properties p = propertiesService.addProperties(properties);
		return new ResponseEntity<Properties>(p, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-properties")
	public ResponseEntity<Properties> updateProperties(@RequestBody Properties properties) throws BadRequestException {
		Properties p = propertiesService.updateProperties(properties);
		return new ResponseEntity<Properties>(p, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/properties/delete/{id}")
	public ResponseEntity<Properties> deleteProperties(@PathVariable int id) {
		propertiesService.deleteProperties(id);
		return new ResponseEntity<Properties>(HttpStatus.OK);
	}
}
