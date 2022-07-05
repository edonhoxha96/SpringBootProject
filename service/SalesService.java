package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.ProductSupplier;
import com.example.demo.model.Sales;
import com.example.demo.model.Stock;
import com.example.demo.repository.SalesRepository;

@Service
public class SalesService {
	@Autowired
	private SalesRepository salesRepository;
	@Autowired
	private ProductSupplierService productSupplierService;
	@Autowired
	private StockService stockService;
	
	public List<Sales> getAllSales(){
		List<Sales> sales = new ArrayList<>();
		salesRepository.findAll().forEach(sales::add);
		return sales;
	}
	
	public List<Sales> getSalesByDate(String start, String end){
		System.out.println(start);
		LocalDateTime startDate = LocalDateTime.parse(start);
		LocalDateTime endDate = LocalDateTime.parse(end);
		List<Sales> sales = new ArrayList<>();
		salesRepository.findAll().forEach(s -> {
			if(s.getCreated().isAfter(startDate) && s.getCreated().isBefore(endDate)) {
				sales.add(s);
			}
		});
		return sales;
	}
	
	public Sales findOne(int id) {
		Sales sales= salesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sales not found, Id: "+ id));
		return sales;
	}
	
	public Sales addSales(Sales sales) {
		Stock stock = stockService.findOne(sales.getStock().getId());
		stock.setQuantity((stock.getQuantity() - sales.getQuantity()));
		ProductSupplier ps = stock.getProductSupplier();
		ps.setTotalQuantity((ps.getTotalQuantity() - sales.getQuantity()));
		stockService.updateStock(stock);
		productSupplierService.updateProductSupplier(ps);
		return salesRepository.save(sales);
	}
	
	public Sales updateSales(Sales sales) {
		salesRepository.findById(sales.getId()).orElseThrow(() -> new ResourceNotFoundException("Sales not found, Id: "+ sales.getId()));
		return salesRepository.save(sales);
	}
	
	public void deleteSales(int id) {
		salesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sales not found, Id: "+ id));
		salesRepository.deleteById(id);
	}
}
