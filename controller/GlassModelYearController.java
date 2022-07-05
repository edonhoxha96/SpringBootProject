package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GlassModelYear;
import com.example.demo.service.GlassModelYearService;

@RestController
public class GlassModelYearController {
	@Autowired
	private GlassModelYearService glassModelYearService;
	
	@GetMapping("/glass-model-years")
	public List<GlassModelYear> getAllGlassModelYears(){
		return glassModelYearService.getAllGlassModelYears();
	}
	
	@GetMapping("/glass-model-years/{id}")
	public GlassModelYear findGlassModelYear(@PathVariable int id) {
		return glassModelYearService.findOne(id);
	}
	
	@PostMapping("/add-glass-model-year")
	public void addGlassModelYear(@RequestBody GlassModelYear glassModelYear) {
		glassModelYearService.addGlassModelYear(glassModelYear);
	}
	
	@DeleteMapping("/glass-model-years/delete/{id}")
	public void deleteGlassModelYear(@PathVariable int id) {
		glassModelYearService.deleteGlassModelYear(id);
	}
}
