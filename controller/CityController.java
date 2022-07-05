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
import com.example.demo.model.City;
import com.example.demo.service.CityService;

@RestController
public class CityController {
	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities")
	public List<City> getAllCitys(){
		return cityService.getAllCitys();
	}
	
	@GetMapping("/cities/{id}")
	public ResponseEntity<City> findCity(@PathVariable int id) {
		City city = cityService.findOne(id);
		return new ResponseEntity<City>(city,HttpStatus.OK);
	}
	
	@PostMapping("/add-city")
	public ResponseEntity<City> addCity(@RequestBody City city) throws BadRequestException, AlreadyExistsException {
		City newCity = cityService.addCity(city);
		return new ResponseEntity<City>(newCity,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-city")
	public ResponseEntity<City> updateCity(@RequestBody City city) throws BadRequestException {
		City updatedCity =cityService.updateCity(city);
		return new ResponseEntity<City>(updatedCity,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/cities/delete/{id}")
	public ResponseEntity<City> deleteCity(@PathVariable int id) {
		cityService.deleteCity(id);
		return new ResponseEntity<City>(HttpStatus.OK);
	}
}
