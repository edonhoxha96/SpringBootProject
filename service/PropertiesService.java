package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Properties;
import com.example.demo.repository.PropertiesRepository;

@Service
public class PropertiesService {
	@Autowired
	private PropertiesRepository propertiesRepository;
	
	public List<Properties> getAllPropertiess(){
		List<Properties> propertiess = new ArrayList<>();
		propertiesRepository.findAll().forEach(propertiess::add);
		return propertiess;
	}
	
	public Properties findOne(int id) {
		Properties properties= propertiesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property not found, Id: "+ id));
		return properties;
	}
	
	public Properties addProperties(Properties properties) throws BadRequestException, AlreadyExistsException {
		if(properties.getName().trim().isEmpty()) {
			throw new BadRequestException("Property name cannot be empty!");
		}
		if(propertiesRepository.existsByName(properties.getName())) {
			throw new AlreadyExistsException("Property with name \"" + properties.getName() + "\" already exists!");
		}
		return propertiesRepository.save(properties);
	}
	
	public Properties updateProperties(Properties properties) throws BadRequestException {
		propertiesRepository.findById(properties.getId()).orElseThrow(() -> new ResourceNotFoundException("Property not found, Id: "+ properties.getId()));
		if(properties.getName().trim().isEmpty()) {
			throw new BadRequestException("Property name cannot be empty!");
		}
		return propertiesRepository.save(properties);
	}
	
	public void deleteProperties(int id) {
		propertiesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property not found, Id: "+ id));
		propertiesRepository.deleteById(id);
	}
}
