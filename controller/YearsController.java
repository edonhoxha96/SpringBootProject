package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.example.demo.model.Years;
import com.example.demo.repository.YearsRepository;
import com.example.demo.service.YearsService;

@RestController
public class YearsController {
	@Autowired
	private YearsService yearsService;
	@Autowired
	private YearsRepository yearsRepo;
	
	@GetMapping("/years")
	public List<Years> getAllYears(){
		return yearsService.getAllYearss();
	}
	
	@GetMapping("/years/{id}")
	public ResponseEntity<Years> findYears(@PathVariable int id) {
		Years years = yearsService.findOne(id);
		return new ResponseEntity<Years>(years, HttpStatus.OK);
	}
	
	@GetMapping("/years/model/{id}")
	public ResponseEntity<Set<Years>> findYearsOfModel(@PathVariable int id) {
		Set<Years> years = yearsService.findYearsOfModel(id);
		return new ResponseEntity<>(years, HttpStatus.OK);
	}
	
	@PostMapping("/add-years")
	public ResponseEntity<Years> addYears(@RequestBody Years years) throws BadRequestException, AlreadyExistsException {
		Years y = yearsService.addYears(years);
		return new ResponseEntity<Years>(y, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-years")
	public ResponseEntity<Years> updateYears(@RequestBody Years years) throws BadRequestException {
		Years y = yearsService.updateYears(years);
		return new ResponseEntity<Years>(y, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/years/delete/{id}")
	public ResponseEntity<Years> deleteYears(@PathVariable int id) {
		yearsService.deleteYears(id);
		return new ResponseEntity<Years>(HttpStatus.OK);
	}
	
	@GetMapping("pagination-years")
	public ResponseEntity<Map<String, Object>> getPaginatedYears(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		List<Years> years = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<Years> yearsPage;
		if(name == null) {
			yearsPage = yearsRepo.findAll(paging);
		}else {
			yearsPage = yearsRepo.findByNameContainingIgnoreCase(name, paging);
		}
		years = yearsPage.getContent();
		
		Map<String, Object> response = new HashMap<>();
		response.put("years", years);
		response.put("currentPage", yearsPage.getNumber());
		response.put("totalItems", yearsPage.getTotalElements());
		response.put("totalPages", yearsPage.getTotalPages());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
