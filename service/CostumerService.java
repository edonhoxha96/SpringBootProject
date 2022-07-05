package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Costumer;
import com.example.demo.repository.CostumerRepository;

@Service
public class CostumerService {
	@Autowired
	private CostumerRepository costumerRepository;
	
	public List<Costumer> getAllCostumers(){
		List<Costumer> costumers = new ArrayList<>();
		costumerRepository.findAll().forEach(costumers::add);
		return costumers;
	}
	
	public Costumer findOne(int id) {
		Costumer costumer= costumerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Costumer not found, Id: "+ id));
		return costumer;
	}
	
	public Costumer addCostumer(Costumer costumer) {
		return costumerRepository.save(costumer);
	}
	
	public Costumer updateCostumer(Costumer costumer) {
		costumerRepository.findById(costumer.getId()).orElseThrow(() -> new ResourceNotFoundException("Costumer not found, Id: "+ costumer.getId()));
		return costumerRepository.save(costumer);
	}
	
	public void deleteCostumer(int id) {
		costumerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Costumer not found, Id: "+ id));
		costumerRepository.deleteById(id);
	}
}
