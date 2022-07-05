package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.BodyVariant;
import com.example.demo.repository.BodyVariantRepository;

@Service
public class BodyVariantService {
	@Autowired
	private BodyVariantRepository bodyVariantRepository;
	
	public List<BodyVariant> getAllBodyVariants(){
		List<BodyVariant> bodyVariant = new ArrayList<>();
		bodyVariantRepository.findAll().forEach(bodyVariant::add);
		return bodyVariant;
	}
	
	public BodyVariant findOne(int id) {
		BodyVariant bodyVariant = bodyVariantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Body Variant not found, Id: " + id));
		return bodyVariant;
	}
	
	public BodyVariant addBodyVariant(BodyVariant bodyVariant) throws BadRequestException, AlreadyExistsException {
		if(bodyVariant.getName().trim().isEmpty()) {
			throw new BadRequestException("Body Variant name cannot be empty!");
		}
		if(bodyVariantRepository.existsByName(bodyVariant.getName())) {
			throw new AlreadyExistsException("Body Variant with name \"" + bodyVariant.getName() + "\" already exists!");
		}
		return bodyVariantRepository.save(bodyVariant);
	}
	
	public BodyVariant updateBodyVariant(BodyVariant bodyVariant) throws BadRequestException{
		bodyVariantRepository.findById(bodyVariant.getId()).orElseThrow(() -> new ResourceNotFoundException("Body Variant not found, Id: " + bodyVariant.getId()));
		if(bodyVariant.getName().trim().isEmpty()) {
			throw new BadRequestException("Body Variant name cannot be empty!");
		}
		return bodyVariantRepository.save(bodyVariant);
	}
	
	public void deleteBodyVariant(int id) {
		bodyVariantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Body Variant not found, Id: " + id));
		bodyVariantRepository.deleteById(id);
	}
}
