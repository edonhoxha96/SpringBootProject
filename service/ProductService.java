package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	
	public List<Product> getProductsByModel(int id) {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(product -> {
			if(product.getModel().getId() == id) {
				products.add(product);
			}
		});
		return products;
	}
	
	public List<Product> getProductsByModelAndYear(int modelid, int yearid) {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(product -> {
			if(product.getModel().getId() == modelid && product.getYears().getId() == yearid) {
				products.add(product);
			}
		});
		return products;
	}
	
	public Product findOne(int id) {
		Product product= productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, Id: "+ id)); 
		return product;
	}
	
	public List<Product> findByEurocode(String e){
		return productRepository.findByEurocodeContainingIgnoreCase(e);
	}
	
	public List<Product> findByDescription(String s){
		return productRepository.findByDescriptionContainingIgnoreCase(s);
	}
	
	public Product addProduct(Product product) throws AlreadyExistsException, BadRequestException{
		if(product.getEurocode().trim().isEmpty()) {
			throw new BadRequestException("Product Eurocode cannot be empty!");
		}
		if(productRepository.existsByEurocode(product.getEurocode())) {
			throw new AlreadyExistsException("Product with eurocode \"" + product.getEurocode() + "\" already exists!");
		}
		if(product.getModel() == null) {
			throw new BadRequestException("Product Model cannot be null!");
		}
		product.setEurocode(product.getEurocode().toUpperCase());
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product product) throws BadRequestException{
		productRepository.findById(product.getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found, Id: "+ product.getId()));
		if(product.getEurocode().trim().isEmpty()) {
			throw new BadRequestException("Product Eurocode cannot be empty!");
		}
		product.setEurocode(product.getEurocode().toUpperCase());
		return productRepository.save(product);
	}
	
	public void deleteProduct(int id) {
		productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, Id: "+ id));
		productRepository.deleteById(id);
	}
}
