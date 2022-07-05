package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.GlassBodyVariant;
import com.example.demo.repository.GlassBodyVariantRepository;

@Service
public class GlassBodyVariantService {
	@Autowired
	private GlassBodyVariantRepository glassBodyVariantRepository;
	
	public List<GlassBodyVariant> getAllGlassBodyVariants(){
		List<GlassBodyVariant> glassBodyVariant = new ArrayList<>();
		glassBodyVariantRepository.findAll().forEach(glassBodyVariant::add);
		return glassBodyVariant;
	}
	
	public GlassBodyVariant findOne(int id) {
		GlassBodyVariant glassBodyVariant = glassBodyVariantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Glass Body variant not found, Id: "+ id));
		return glassBodyVariant;
	}
	
	public List<GlassBodyVariant> findGlassBodyVariantsByProduct(int id){
		List<GlassBodyVariant> gb = new ArrayList<>();
		glassBodyVariantRepository.findAll().forEach(g -> {
			if(g.getProduct().getId() == id) {
				gb.add(g);
			}
		});
		return gb;
	}
	
	public GlassBodyVariant addGlassBodyVariant(GlassBodyVariant glassBodyVariant) {
		return glassBodyVariantRepository.save(glassBodyVariant);
	}
	
	public GlassBodyVariant updateGlassBodyVariant(GlassBodyVariant glassBodyVariant) {
		glassBodyVariantRepository.findById(glassBodyVariant.getId()).orElseThrow(() -> new ResourceNotFoundException("Glass Body variant not found, Id: "+ glassBodyVariant.getId()));
		return glassBodyVariantRepository.save(glassBodyVariant);
	}
	
	public void deleteGlassBodyVariant(int id) {
		glassBodyVariantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Glass Body variant not found, Id: "+ id));
		glassBodyVariantRepository.deleteById(id);
	}
}
