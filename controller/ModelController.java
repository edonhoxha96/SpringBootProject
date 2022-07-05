package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Model;
import com.example.demo.repository.ModelRepository;
import com.example.demo.service.ModelService;

@RestController
public class ModelController {
	@Autowired
	private ModelService modelService;
	@Autowired
	private ModelRepository modelRepository;
	
	@GetMapping("/models")
	public List<Model> getAllModels(){
		return modelService.getAllModels();
	}
	
	@GetMapping("/models/{id}")
	public ResponseEntity<Model> findModel(@PathVariable int id) {
		Model model = modelService.findOne(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@GetMapping("/models/brand/{id}")
	public List<Model> findModelByBrandId(@PathVariable int id) {
		return modelService.getAllModelsByBrandId(id);
	}	
	
	@PostMapping("/add-model")
	public ResponseEntity<Model> addModel(@RequestBody Model model) throws BadRequestException, AlreadyExistsException{
		Model m = modelService.addModel(model);
		return new ResponseEntity<>(m, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-model")
	public ResponseEntity<Model> updateModel(@RequestBody Model model) throws BadRequestException {
		Model m = modelService.updateModel(model);
		return new ResponseEntity<>(m, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/models/delete/{id}")
	public ResponseEntity<Model> deleteModel(@PathVariable int id) {
		modelService.deleteModel(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("pagination-models")
	public ResponseEntity<Map<String, Object>> getPaginatedModels(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		List<Model> models = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<Model> modelPage;
		if(name == null) {
			modelPage = modelRepository.findAll(paging);
		}else {
			modelPage = modelRepository.findByNameContainingIgnoreCase(name, paging);
		}
		models = modelPage.getContent();
		
		Map<String, Object> response = new HashMap<>();
		response.put("models", models);
		response.put("currentPage", modelPage.getNumber());
		response.put("totalItems", modelPage.getTotalElements());
		response.put("totalPages", modelPage.getTotalPages());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
