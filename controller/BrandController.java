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
//import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.demo.model.Brand;
import com.example.demo.repository.BrandRepository;
import com.example.demo.service.BrandService;

@RestController
public class BrandController {
	@Autowired
	private BrandService brandService;
	@Autowired
	private BrandRepository brandRepository;
	
	//@PreAuthorize("hasAuthority('1') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
	@GetMapping("/brands")
	public List<Brand> getAllBrands(){
		return brandService.getAllBrands();
	}
	
	@GetMapping("/brands/{id}")
	public ResponseEntity<Brand> findBrand(@PathVariable int id) {
		Brand brand = brandService.findOne(id);
		return new ResponseEntity<Brand>(brand, HttpStatus.OK);
	}
	
	@PostMapping("/add-brand")
	public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) throws BadRequestException, AlreadyExistsException {
		return brandService.addBrand(brand);
	}
	
	@PutMapping("/update-brand")
	public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) throws BadRequestException {
		Brand updatedBrand = brandService.updateBrand(brand);
		return new ResponseEntity<Brand>(updatedBrand, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/brands/delete/{id}")
	public ResponseEntity<Brand> deleteBrand(@PathVariable int id) {
		brandService.deleteBrand(id);
		return new ResponseEntity<Brand>(HttpStatus.OK);
	}
	
	@GetMapping("pagination-brands")
	public ResponseEntity<Map<String, Object>> getPaginatedBrands(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		List<Brand> brands = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<Brand> brandPage;
		if(name == null) {
			brandPage = brandRepository.findAll(paging);
		}else {
			brandPage = brandRepository.findByNameContainingIgnoreCase(name, paging);
		}
		brands = brandPage.getContent();
		
		Map<String, Object> response = new HashMap<>();
		response.put("brands", brands);
		response.put("currentPage", brandPage.getNumber());
		response.put("totalItems", brandPage.getTotalElements());
		response.put("totalPages", brandPage.getTotalPages());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
