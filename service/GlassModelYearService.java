package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GlassModelYear;
import com.example.demo.repository.GlassModelYearRepository;

@Service
public class GlassModelYearService {
	@Autowired
	private GlassModelYearRepository glassModelYearRepository;
	
	public List<GlassModelYear> getAllGlassModelYears(){
		List<GlassModelYear> glassModelYear = new ArrayList<>();
		glassModelYearRepository.findAll().forEach(glassModelYear::add);
		return glassModelYear;
	}
	
	public GlassModelYear findOne(int id) {
		GlassModelYear glassModelYear = glassModelYearRepository.findById(id).orElse(null);
		return glassModelYear;
	}
	
	public void addGlassModelYear(GlassModelYear glassModelYear) {
		glassModelYearRepository.save(glassModelYear);
	}
	
	public void deleteGlassModelYear(int id) {
		glassModelYearRepository.deleteById(id);
	}
}
