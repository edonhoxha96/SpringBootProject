package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.example.demo.model.Sales;
import com.example.demo.service.SalesService;

@RestController
public class SalesController {
	@Autowired
	private SalesService salesService;
	
	@GetMapping("/sales")
	public List<Sales> getAllSales(){
		return salesService.getAllSales();
	}
	
	@GetMapping("/sales/{id}")
	public ResponseEntity<Sales> findSales(@PathVariable int id) {
		Sales sales = salesService.findOne(id);
		return new ResponseEntity<Sales>(sales, HttpStatus.OK);
	}
	
	@PostMapping("/add-sales")
	public ResponseEntity<Sales> addSales(@RequestBody Sales sales) {
		Sales s = salesService.addSales(sales);
		return new ResponseEntity<Sales>(s, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-sales")
	public ResponseEntity<Sales> updateSales(@RequestBody Sales sales) {
		Sales s = salesService.updateSales(sales);
		return new ResponseEntity<Sales>(s, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/sales/delete/{id}")
	public ResponseEntity<Sales> deleteSales(@PathVariable int id) {
		salesService.deleteSales(id);
		return new ResponseEntity<Sales>(HttpStatus.OK);
	}
	
	@GetMapping("/sales/date")
	public List<Sales> getSalesByDate(@RequestParam String startDate, @RequestParam String endDate){
		return salesService.getSalesByDate(startDate, endDate);
	}
	
	@GetMapping("/pagination-sales")
	public ResponseEntity<Map<String, Object>> getPaginationSales(
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "3") int size,
			@RequestParam String startDate,
			@RequestParam String endDate){
		System.out.println("TEST1");
		List<Sales> sales = new ArrayList<Sales>();
		Pageable paging = PageRequest.of(page, size);
		List<Sales> salesByDate = salesService.getSalesByDate(startDate, endDate);
		double totalAmount = salesByDate.stream().mapToDouble(s -> (s.getSellingPrice() * s.getQuantity())).sum();
		final int start = (int)paging.getOffset();
		final int end = Math.min((start + paging.getPageSize()), salesByDate.size());
		Page<Sales> pageSales = new PageImpl<Sales>(salesByDate.subList(start, end), paging, salesByDate.size());
		sales = pageSales.getContent();
		
		Map<String, Object> response = new HashMap<>();
		response.put("sales", sales);
		response.put("currentPage", pageSales.getNumber());
		response.put("totalItems", pageSales.getTotalElements());
		response.put("totalPages", pageSales.getTotalPages());
		response.put("totalAmount", totalAmount);
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
