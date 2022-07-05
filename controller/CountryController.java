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
import com.example.demo.model.Country;
import com.example.demo.service.CountryService;

@RestController
public class CountryController {
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/countries")
	public List<Country> getAllCountries(){
		return countryService.getAllCountrys();
	}
	
	@GetMapping("/countries/{id}")
	public ResponseEntity<Country> findCountry(@PathVariable int id) {
		Country country = countryService.findOne(id);
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}
	
	@PostMapping("/add-country")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) throws BadRequestException, AlreadyExistsException{
		Country c = countryService.addCountry(country);
		return new ResponseEntity<Country>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-country")
	public ResponseEntity<Country> updateCountry(@RequestBody Country country) throws BadRequestException{
		Country c = countryService.updateCountry(country);
		return new ResponseEntity<Country>(c, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/countries/delete/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable int id) {
		countryService.deleteCountry(id);
		return new ResponseEntity<Country>(HttpStatus.OK);
	}
}
