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
import com.example.demo.model.BodyVariant;
import com.example.demo.model.Brand;
import com.example.demo.service.BodyVariantService;

@RestController
public class BodyVariantController {
	@Autowired
	private BodyVariantService bodyVariantService;
	
	@GetMapping("/body-variants")
	public List<BodyVariant> getAllBodyVariants(){
		return bodyVariantService.getAllBodyVariants();
	}
	
	@GetMapping("/body-variants/{id}")
	public ResponseEntity<BodyVariant> findBodyVariant(@PathVariable int id) {
		BodyVariant bv = bodyVariantService.findOne(id);
		return new ResponseEntity<BodyVariant>(bv, HttpStatus.OK);
	}
	
	@PostMapping("/add-body-variant")
	public ResponseEntity<BodyVariant> addBodyVariant(@RequestBody BodyVariant bodyVariant) throws BadRequestException, AlreadyExistsException{
		BodyVariant bv = bodyVariantService.addBodyVariant(bodyVariant);
		return new ResponseEntity<>(bv, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-body-variant")
	public ResponseEntity<BodyVariant> updateBodyVariant(@RequestBody BodyVariant bodyVariant) throws BadRequestException{
		BodyVariant bv = bodyVariantService.updateBodyVariant(bodyVariant);
		return new ResponseEntity<>(bv, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/body-variants/delete/{id}")
	public ResponseEntity<Brand> deleteBodyVariant(@PathVariable int id) {
		bodyVariantService.deleteBodyVariant(id);
		return new ResponseEntity<Brand>(HttpStatus.OK);
	}
}
