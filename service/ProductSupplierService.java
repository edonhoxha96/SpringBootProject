package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.ProductSupplier;
import com.example.demo.repository.ProductSupplierRepository;

@Service
public class ProductSupplierService {
	@Autowired
	private ProductSupplierRepository productSupplierRepository;
	
	public List<ProductSupplier> getAllProductSuppliers(){
		List<ProductSupplier> productSuppliers = new ArrayList<>();
		productSupplierRepository.findAll().forEach(productSuppliers::add);
		return productSuppliers;
	}
	
	public ProductSupplier findOne(int id) {
		ProductSupplier productSupplier= productSupplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductSupplier not found, Id: "+ id));
		return productSupplier;
	}
	
	public List<ProductSupplier> getLowProductSuppliers() {
		List<ProductSupplier> productSuppliers = new ArrayList<>();
		productSupplierRepository.findAll().forEach(ps -> {
			if(ps.getTotalQuantity() <= ps.getMinQuantity()) {
				productSuppliers.add(ps);
			}
		});
		return productSuppliers;
	}
	
	public List<ProductSupplier> getByModelAndYear(int modId, int yearId){
		List<ProductSupplier> productSuppliers = new ArrayList<>();
		productSupplierRepository.findAll().forEach(ps -> {
			if(ps.getProduct().getModel().getId() == modId && ps.getProduct().getYears().getId() == yearId) {
				productSuppliers.add(ps);
			}
		});
		return productSuppliers;
	}
	
	public List<ProductSupplier> getByProduct(int id){
		List<ProductSupplier> productSuppliers = new ArrayList<>();
		productSupplierRepository.findAll().forEach(ps -> {
			if(ps.getProduct().getId() == id) {
				productSuppliers.add(ps);
			}
		});
		return productSuppliers;
	}
	
	public ProductSupplier addProductSupplier(ProductSupplier productSupplier) throws AlreadyExistsException{
//		List<ProductSupplier> ps = new ArrayList<>();
//		productSupplierRepository.findAll().forEach(ps::add);
//		ps = ps.stream().filter(p -> p.getProduct().getId() == productSupplier.getProduct().getId() &&  p.getSupplier().getId() == productSupplier.getSupplier().getId()).collect(Collectors.toList());
//		System.out.println("Size: " + ps.size());
//		if(ps.size() > 0) {
//			throw new AlreadyExistsException("ProductSupplier already exists!");
//		}
		if(productSupplierRepository.existsByProductAndSupplier(productSupplier.getProduct(), productSupplier.getSupplier())) {
			throw new AlreadyExistsException("ProductSupplier already exists!");
		}
		return productSupplierRepository.save(productSupplier);
	}
	
	public ProductSupplier updateProductSupplier(ProductSupplier productSupplier) {
		productSupplierRepository.findById(productSupplier.getId()).orElseThrow(() -> new ResourceNotFoundException("ProductSupplier not found, Id: "+ productSupplier.getId()));
		return productSupplierRepository.save(productSupplier);
	}
	
	public void deleteProductSupplier(int id) {
		productSupplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductSupplier not found, Id: "+ id));
		productSupplierRepository.deleteById(id);
	}
}
