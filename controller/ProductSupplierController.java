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
import com.example.demo.model.ProductSupplier;
import com.example.demo.repository.ProductSupplierRepository;
import com.example.demo.service.ProductSupplierService;

@RestController
public class ProductSupplierController {
	@Autowired
	private ProductSupplierService productSupplierService;
	@Autowired
	private ProductSupplierRepository productSupplierRepository;
	
	@GetMapping("/productSuppliers")
	public List<ProductSupplier> getAllProductSuppliers(){
		return productSupplierService.getAllProductSuppliers();
	}
	
	@GetMapping("/productSuppliers/{id}")
	public ResponseEntity<ProductSupplier> findProductSupplier(@PathVariable int id) {
		ProductSupplier productSupplier = productSupplierService.findOne(id);
		return new ResponseEntity<ProductSupplier>(productSupplier, HttpStatus.OK);
	}
	
	@GetMapping("/productSuppliers/low")
	public ResponseEntity<List<ProductSupplier>> getLowProductSuppliers(){
		return new ResponseEntity<List<ProductSupplier>>(productSupplierService.getLowProductSuppliers(), HttpStatus.OK);
	}
	
	@GetMapping("/productSuppliers/model-year")
	public ResponseEntity<List<ProductSupplier>> getByModelAndYear(@RequestParam int modId, @RequestParam int yearId){
		return new ResponseEntity<List<ProductSupplier>>(productSupplierService.getByModelAndYear(modId, yearId), HttpStatus.OK);
	}
	
	@GetMapping("/productSuppliers/product/{id}")
	public ResponseEntity<List<ProductSupplier>> getByProduct(@PathVariable int id){
		return new ResponseEntity<List<ProductSupplier>>(productSupplierService.getByProduct(id), HttpStatus.OK);
	}
	
	@PostMapping("/add-productSupplier")
	public ResponseEntity<ProductSupplier> addProductSupplier(@RequestBody ProductSupplier productSupplier) throws AlreadyExistsException{
			ProductSupplier ps = productSupplierService.addProductSupplier(productSupplier);
			return new ResponseEntity<ProductSupplier>(ps, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-productSupplier")
	public ResponseEntity<ProductSupplier> updateProductSupplier(@RequestBody ProductSupplier productSupplier) {
		ProductSupplier ps = productSupplierService.updateProductSupplier(productSupplier);
		return new ResponseEntity<ProductSupplier>(ps, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/productSuppliers/delete/{id}")
	public ResponseEntity<ProductSupplier> deleteProductSupplier(@PathVariable int id) {
		productSupplierService.deleteProductSupplier(id);
		return new ResponseEntity<ProductSupplier>(HttpStatus.OK);
	}
	
	@GetMapping("pagination-product-suppliers")
	public ResponseEntity<Map<String,Object>> getPaginatedProductSuppliers(
			@RequestParam(required = false) String eurocode,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		List<ProductSupplier> productSuppliers = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<ProductSupplier> pageProductSuppliers;
		if(eurocode == null) {
			pageProductSuppliers = productSupplierRepository.findAll(paging);
		}else {
			pageProductSuppliers = productSupplierRepository.findByProductEurocode(eurocode, paging);
		}
		productSuppliers = pageProductSuppliers.getContent();
		
		Map<String, Object> response = new HashMap<>();
		response.put("productSuppliers", productSuppliers);
		response.put("currentPage", pageProductSuppliers.getNumber());
		response.put("totalItems", pageProductSuppliers.getTotalElements());
		response.put("totalPages", pageProductSuppliers.getTotalPages());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
