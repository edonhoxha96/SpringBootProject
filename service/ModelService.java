package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Model;
import com.example.demo.repository.ModelRepository;

@Service
public class ModelService {
	@Autowired
	private ModelRepository modelRepository;
	
	public List<Model> getAllModels(){
		List<Model> models = new ArrayList<>();
		modelRepository.findAll().forEach(models::add);
		return models;
	}
	
	public List<Model> getAllModelsByBrandId(int id){
		List<Model> models = new ArrayList<>();
		modelRepository.findAll().forEach(model -> {
			if(model.getBrand().getId() == id) 
				models.add(model);
				});
		Collections.sort(models, (o1, o2) -> (o1.getName().compareTo(o2.getName())));
		return models;
	}	
	
	public Model findOne(int id) {
		Model model= modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Model not found, Id: "+ id));
		return model;
	}
	
	public Model addModel(Model model) throws BadRequestException, AlreadyExistsException{
		if(model.getName().trim().isEmpty()) {
			throw new BadRequestException("Model name can't be empty!");
		}
		if(modelRepository.existsByName(model.getName())) {
			throw new AlreadyExistsException("Model with name \"" + model.getName() + "\" already exists!");
		}
		return modelRepository.save(model);
	}
	
	public Model updateModel(Model model) throws BadRequestException{
		modelRepository.findById(model.getId()).orElseThrow(() -> new ResourceNotFoundException("Model not found, Id: "+ model.getId()));
		if(model.getName().trim().isEmpty()) {
			throw new BadRequestException("Model name can't be empty!");
		}
		return modelRepository.save(model);
	}
	
	public void deleteModel(int id) {
		modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Model not found, Id: "+ id));
		modelRepository.deleteById(id);
	}
}
