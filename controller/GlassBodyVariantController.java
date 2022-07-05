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

import com.example.demo.model.GlassBodyVariant;
import com.example.demo.service.GlassBodyVariantService;

@RestController
public class GlassBodyVariantController {
	@Autowired
	private GlassBodyVariantService glassBodyVariantService;
	
	@GetMapping("/glass-body-variants")
	public List<GlassBodyVariant> getAllGlassBodyVariants(){
		return glassBodyVariantService.getAllGlassBodyVariants();
	}
	
	@GetMapping("/glass-body-variants/{id}")
	public ResponseEntity<GlassBodyVariant> findGlassBodyVariant(@PathVariable int id) {
		GlassBodyVariant gb = glassBodyVariantService.findOne(id);
		return new ResponseEntity<GlassBodyVariant>(gb, HttpStatus.OK);
	}
	
	@GetMapping("/glass-body-variants-by-product/{id}")
	public ResponseEntity<List<GlassBodyVariant>> findGlassBodyVariantByProduct(@PathVariable int id) {
		List<GlassBodyVariant> gb = glassBodyVariantService.findGlassBodyVariantsByProduct(id);
		return new ResponseEntity<List<GlassBodyVariant>>(gb, HttpStatus.OK);
	}
	
	@PostMapping("/add-glass-body-variant")
	public ResponseEntity<GlassBodyVariant> addGlassBodyVariant(@RequestBody GlassBodyVariant glassBodyVariant) {
		GlassBodyVariant gb = glassBodyVariantService.addGlassBodyVariant(glassBodyVariant);
		return new ResponseEntity<GlassBodyVariant>(gb, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-glass-body-variant")
	public ResponseEntity<GlassBodyVariant> updateGlassBodyVariant(@RequestBody GlassBodyVariant glassBodyVariant) {
		GlassBodyVariant gb = glassBodyVariantService.updateGlassBodyVariant(glassBodyVariant);
		return new ResponseEntity<GlassBodyVariant>(gb, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/glass-body-variants/delete/{id}")
	public ResponseEntity<GlassBodyVariant> deleteGlassBodyVariant(@PathVariable int id) {
		glassBodyVariantService.deleteGlassBodyVariant(id);
		return new ResponseEntity<GlassBodyVariant>(HttpStatus.OK);
	}
}
