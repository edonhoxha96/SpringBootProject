package com.example.demo.service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.GlassType;
import com.example.demo.repository.GlassTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service; 
import java.util.List;
import java.util.ArrayList; 

@Service
public class GlassTypeService {
	@Autowired
	private GlassTypeRepository glassTypeRepository;
	
	public List<GlassType> getAllGlassTypes(){
		List<GlassType> glassTypes = new ArrayList<>();
		glassTypeRepository.findAll().forEach(glassTypes::add);
		return glassTypes;
	}
	
	public GlassType findOne(int id) {
		GlassType glassType = glassTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("GlassType not found, Id: "+ id));
		return glassType;
	}
	
	public GlassType addGlassType(GlassType glassType) throws BadRequestException, AlreadyExistsException {
		if(glassType.getName().trim().isEmpty()) {
			throw new BadRequestException("GlassType name cannot be empty!");
		}
		if(glassTypeRepository.existsByName(glassType.getName())) {
			throw new AlreadyExistsException("GlassType with name \"" + glassType.getName() + "\" already exists!");
		}
		return glassTypeRepository.save(glassType);
	}
	
	public GlassType updateGlassType(GlassType glassType) throws BadRequestException {
		glassTypeRepository.findById(glassType.getId()).orElseThrow(() -> new ResourceNotFoundException("GlassType not found, Id: "+ glassType.getId()));
		if(glassType.getName().trim().isEmpty()) {
			throw new BadRequestException("GlassType name cannot be empty!");
		}
		return glassTypeRepository.save(glassType);
	}
	
	public void deleteGlassType(int id) {
		glassTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("GlassType not found, Id: "+ id));
		glassTypeRepository.deleteById(id);
	}
}