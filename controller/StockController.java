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

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;

@RestController
public class StockController {
	@Autowired
	private StockService stockService;
	@Autowired
	private StockRepository stockRepository;
	
	@GetMapping("/stocks")
	public List<Stock> getAllStocks(){
		return stockService.getAllStocks();
	}
	
	@GetMapping("/stockByProduct/{id}")
	public List<Stock> getStockByProduct(@PathVariable int id){
		return stockService.getStockByProduct(id);
	}
	
	@GetMapping("/stock/calculate-average")
	public double calculateAverage(
			@RequestParam(required = true) int id,
			@RequestParam(required = true) int quantity,
			@RequestParam(required = true) double buyingPrice){
		return stockService.calculateAverage(id, quantity, buyingPrice);
	}
	
	@GetMapping("/stocks/{id}")
	public ResponseEntity<Stock> findStock(@PathVariable int id) {
		Stock stock = stockService.findOne(id);
		return new ResponseEntity<Stock>(stock, HttpStatus.OK);
	}
	
	@PostMapping("/add-stock")
	public ResponseEntity<Stock> addStock(@RequestBody Stock stock) throws BadRequestException{
		Stock s = stockService.addStock(stock);
		return new ResponseEntity<Stock>(s, HttpStatus.CREATED);
	}
	
	@PutMapping("update-stock")
	public ResponseEntity<Stock> updateStock(@RequestBody Stock stock) {
		Stock s = stockService.updateStock(stock);
		return new ResponseEntity<Stock>(s, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/stocks/delete/{id}")
	public ResponseEntity<Stock> deleteStock(@PathVariable int id) {
		stockService.deleteStock(id);
		return new ResponseEntity<Stock>(HttpStatus.OK);
	}
	
	@PostMapping("/move-stock")
	public void moveStock(@RequestBody Stock stock, @RequestParam int quantity) {
		stockService.moveStock(stock, quantity);
	}
	
	@PostMapping("/move-sector")
	public Stock moveSector(@RequestBody Stock stock, @RequestParam int quantity, @RequestParam int secId) {
		return stockService.moveSector(stock, quantity, secId);
	}
	
	@GetMapping("pagination-stock")
	public ResponseEntity<Map<String, Object>> getPaginationStock(@RequestParam(required = false) int sectorId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size){
		List<Stock> stocks = new ArrayList<Stock>();
		Pageable paging = PageRequest.of(page, size);
		Page<Stock> pageStock;
		if(sectorId == 0) {
			pageStock = stockRepository.findAll(paging);
		}else {
			pageStock = stockRepository.findBySectorId(sectorId, paging);
		}
		stocks = pageStock.getContent();
		
		Map<String, Object> response = new HashMap<>();
		response.put("stocks", stocks);
		response.put("currentPage", pageStock.getNumber());
		response.put("totalItems", pageStock.getTotalElements());
		response.put("totalPages", pageStock.getTotalPages());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
