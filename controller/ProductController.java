package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> findProduct(@PathVariable int id) {
		Product product = productService.findOne(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/productsByModel/{id}")
	public List<Product> productsByModel(@PathVariable int id) {
		return productService.getProductsByModel(id);
	}
	
	@GetMapping("/productsByEurocode/{eurocode}")
	public List<Product> productsByEurocode(@PathVariable String eurocode) {
		return productService.findByEurocode(eurocode);
	}
	
	@GetMapping("/productsByDescription/{s}")
	public List<Product> productsByDescription(@PathVariable String s) {
		return productService.findByDescription(s);
	}
	
	@GetMapping("/productsByModelAndYear/{modelid}/{yearid}")
	public List<Product> getProductsByModelAndYear(@PathVariable int modelid, @PathVariable int yearid) {
		return productService.getProductsByModelAndYear(modelid, yearid);
	}
	
	@PostMapping("/add-product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws AlreadyExistsException, BadRequestException{
		Product p = productService.addProduct(product);
		return new ResponseEntity<Product>(p, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws BadRequestException{
		Product p = productService.updateProduct(product);
		return new ResponseEntity<Product>(p, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/products/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
}
