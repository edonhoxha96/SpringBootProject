package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Depot;
import com.example.demo.repository.DepotRepository;

@Service
public class DepotService {
	@Autowired
	private DepotRepository depotRepository;
	
	public List<Depot> getAllDepots(){
		List<Depot> depots = new ArrayList<>();
		depotRepository.findAll().forEach(depots::add);
		return depots;
	}
	
	public Depot findOne(int id) {
		Depot depot= depotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Depot not found, Id: "+ id));
		return depot;
	}
	
	public Depot addDepot(Depot depot) throws BadRequestException, AlreadyExistsException {
		if(depot.getName().trim().isEmpty()) {
			throw new BadRequestException("Depot name cannot be empty!");
		}
		if(depotRepository.existsByName(depot.getName())) {
			throw new AlreadyExistsException("Depot with name \"" + depot.getName() + "\" already exists!");
		}
		return depotRepository.save(depot);
	}
	
	public Depot updateDepot(Depot depot) throws BadRequestException{
		depotRepository.findById(depot.getId()).orElseThrow(() -> new ResourceNotFoundException("Depot not found, Id: "+ depot.getId()));
		if(depot.getName().trim().isEmpty()) {
			throw new BadRequestException("Depot name cannot be empty!");
		}
		return depotRepository.save(depot);
	}
	
	public void deleteDepot(int id) {
		depotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Depot not found, Id: "+ id));
		depotRepository.deleteById(id);
	}
}
