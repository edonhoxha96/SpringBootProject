package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Sales2;
import com.example.demo.repository.Sales2Repository;

@Service
public class Sales2Service {
	@Autowired
	private Sales2Repository salesRepository;
	
	public List<Sales2> getAllSales2(){
		List<Sales2> sales = new ArrayList<>();
		salesRepository.findAll().forEach(sales::add);
		return sales;
	}
	
	public List<Sales2> getSales2ByDate(String start, String end){
		System.out.println(start);
		LocalDateTime startDate = LocalDateTime.parse(start);
		LocalDateTime endDate = LocalDateTime.parse(end);
		List<Sales2> sales = new ArrayList<>();
		salesRepository.findAll().forEach(s -> {
			if(s.getCreated().isAfter(startDate) && s.getCreated().isBefore(endDate)) {
				sales.add(s);
			}
		});
		return sales;
	}
	
	public Sales2 findOne(int id) {
		Sales2 sales= salesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sales2 not found, Id: "+ id));
		return sales;
	}
	
	public Sales2 addSales2(Sales2 sales) {
		return salesRepository.save(sales);
	}
	
	public Sales2 updateSales2(Sales2 sales) {
		salesRepository.findById(sales.getId()).orElseThrow(() -> new ResourceNotFoundException("Sales2 not found, Id: "+ sales.getId()));
		return salesRepository.save(sales);
	}
	
	public void deleteSales2(int id) {
		salesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sales2 not found, Id: "+ id));
		salesRepository.deleteById(id);
	}
}
