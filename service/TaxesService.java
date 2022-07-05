package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Taxes;
import com.example.demo.repository.TaxesRepository;

@Service
public class TaxesService {
	@Autowired
	private TaxesRepository taxesRepo;
	
	public List<Taxes> getAllTaxes(){
		List<Taxes> taxes = new ArrayList<>();
		taxesRepo.findAll().forEach(taxes::add);
		return taxes;
	}
	
	public Taxes findOne(int id) {
		Taxes tax = taxesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Taxes not found, Id: "+ id));
		return tax;
	}
	
	public Taxes addTaxes(Taxes tax) {
		return taxesRepo.save(tax);
	}
	
	public Taxes updateTaxes(Taxes tax) {
		taxesRepo.findById(tax.getId()).orElseThrow(() -> new ResourceNotFoundException("Taxes not found, Id: "+ tax.getId()));
		return taxesRepo.save(tax);
	}
	
	public void deleteTaxes(int id) {
		taxesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Taxes not found, Id: "+ id));
		taxesRepo.deleteById(id);
	}
}
