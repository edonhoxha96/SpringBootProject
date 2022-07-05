package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.ProductionYear;
import com.example.demo.repository.ProductionYearRepository;

@Service
public class ProductionYearService {
	@Autowired
	private ProductionYearRepository productionYearRepository;
	
	public List<ProductionYear> getAllProductionYears(){
		List<ProductionYear> productionYears = new ArrayList<>();
		productionYearRepository.findAll().forEach(productionYears::add);
		return productionYears;
	}
	
	public ProductionYear findOne(int id) {
		ProductionYear productionYear= productionYearRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductionYear not found, Id: "+ id)); 
		return productionYear;
	}
	
	public ProductionYear addProductionYear(ProductionYear productionYear) {
		return productionYearRepository.save(productionYear);
	}
	
	public ProductionYear updateProductionYear(ProductionYear productionYear) {
		productionYearRepository.findById(productionYear.getId()).orElseThrow(() -> new ResourceNotFoundException("ProductionYear not found, Id: "+ productionYear.getId()));
		return productionYearRepository.save(productionYear);
	}
	
	public void deleteProductionYear(int id) {
		productionYearRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductionYear not found, Id: "+ id));
		productionYearRepository.deleteById(id);
	}
}
