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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Sales2;
import com.example.demo.repository.Sales2Repository;
import com.example.demo.service.Sales2Service;

@RestController
public class Sales2Controller {
	@Autowired
	private Sales2Service sales2Service;
	@Autowired
	private Sales2Repository sales2Repo;
	
	@GetMapping("/sales2")
	public List<Sales2> getAllSales2(){
		return sales2Service.getAllSales2();
	}
	
	@GetMapping("/sales2/{id}")
	public ResponseEntity<Sales2> findSales2(@PathVariable int id) {
		Sales2 sales2 = sales2Service.findOne(id);
		return new ResponseEntity<Sales2>(sales2, HttpStatus.OK);
	}
	
	@PostMapping("/add-sales2")
	public ResponseEntity<Sales2> addSales2(@RequestBody Sales2 sales2) {
		Sales2 s = sales2Service.addSales2(sales2);
		return new ResponseEntity<Sales2>(s, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-sales2")
	public ResponseEntity<Sales2> updateSales2(@RequestBody Sales2 sales2) {
		Sales2 s = sales2Service.updateSales2(sales2);
		return new ResponseEntity<Sales2>(s, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/sales2/delete/{id}")
	public ResponseEntity<Sales2> deleteSales2(@PathVariable int id) {
		sales2Service.deleteSales2(id);
		return new ResponseEntity<Sales2>(HttpStatus.OK);
	}
	
	@GetMapping("/sales2/date")
	public List<Sales2> getSales2ByDate(@RequestParam String startDate, @RequestParam String endDate){
		return sales2Service.getSales2ByDate(startDate, endDate);
	}
	
	@GetMapping("pagination-sales2")
	public ResponseEntity<Map<String, Object>> getPaginatedSales2(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		List<Sales2> sales2 = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<Sales2> sales2Page = sales2Repo.findAll(paging);
		
		sales2 = sales2Page.getContent();
		
		Map<String, Object> response = new HashMap<>();
		response.put("sales2", sales2);
		response.put("currentPage", sales2Page.getNumber());
		response.put("totalItems", sales2Page.getTotalElements());
		response.put("totalPages", sales2Page.getTotalPages());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}	