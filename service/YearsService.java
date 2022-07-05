package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Years;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.YearsRepository;

@Service
public class YearsService {
	@Autowired
	private YearsRepository yearsRepository;
	@Autowired
	private ProductRepository productRepository;
	
	public List<Years> getAllYearss(){
		List<Years> years = new ArrayList<>();
		yearsRepository.findAll().forEach(years::add);
		return years;
	}
	
	public Years findOne(int id) {
		Years year= yearsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Year not found, Id: "+ id));
		return year;
	}
	
	public Set<Years> findYearsOfModel(int id){
		Set<Years> years = new HashSet<>();
		productRepository.findAll().forEach(p -> {
			if(p.getModel().getId() == id) {
				years.add(p.getYears());
			}
		});
		return years;
	}
	
	public Years addYears(Years years) throws BadRequestException, AlreadyExistsException {
		if(years.getName().trim().isEmpty()) {
			throw new BadRequestException("Years name cannot be empty!");
		}
		if(yearsRepository.existsByName(years.getName())) {
			throw new AlreadyExistsException("Years with name \"" + years.getName() + "\" already exists!");
		}
		return yearsRepository.save(years);
	}
	
	public Years updateYears(Years years) throws BadRequestException {
		yearsRepository.findById(years.getId()).orElseThrow(() -> new ResourceNotFoundException("Year not found, Id: "+ years.getId()));
		if(years.getName().trim().isEmpty()) {
			throw new BadRequestException("Years name cannot be empty!");
		}
		return yearsRepository.save(years);
	}
	
	public void deleteYears(int id) {
		yearsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Year not found, Id: "+ id));
		yearsRepository.deleteById(id);
	}
}
