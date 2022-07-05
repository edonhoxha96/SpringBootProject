package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Brand;
import com.example.demo.repository.BrandRepository;


@Service
public class BrandService {
	@Autowired
	private BrandRepository brandRepository;
	
	public List<Brand> getAllBrands(){
		List<Brand> brands = new ArrayList<>();
		brandRepository.findAll().forEach(brands::add);
		return brands;
	}
	
	public Brand findOne(int id){
		Brand brand= brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found, Id: "+ id));
		return brand;
	}
	
	public ResponseEntity<Brand> addBrand(Brand brand) throws BadRequestException, AlreadyExistsException {
		if(brand.getName().trim().isEmpty()) {
			throw new BadRequestException("Brand name cannot be empty!");
		}
		if(brandRepository.existsByName(brand.getName())) {
			throw new AlreadyExistsException("Brand with name \"" + brand.getName() + "\" already exists!");
		}
		brand.setName(brand.getName().substring(0,1).toUpperCase() + brand.getName().substring(1));
		Brand newBrand = brandRepository.save(brand);
		return new ResponseEntity<>(newBrand, HttpStatus.CREATED);
	}
	
	public Brand updateBrand(Brand brand) throws BadRequestException{
		brandRepository.findById(brand.getId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found, Id: "+ brand.getId()));
		if(brand.getName().trim().isEmpty()) {
			throw new BadRequestException("Brand name cannot be empty!");
		}
		
		brand.setName(brand.getName().substring(0,1).toUpperCase() + brand.getName().substring(1));
		return brandRepository.save(brand);
	}
	
	public void deleteBrand(int id) {
		brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found, Id: "+ id));
		brandRepository.deleteById(id);
	}
}
