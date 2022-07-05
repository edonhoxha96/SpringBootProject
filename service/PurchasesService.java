package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Purchases;

import com.example.demo.repository.PurchasesRepository;

@Service
public class PurchasesService {
	@Autowired
	private PurchasesRepository purchasesRepository;
	
	public List<Purchases> getAllPurchases(){
		List<Purchases> purchases = new ArrayList<>();
		purchasesRepository.findAll().forEach(purchases::add);
		return purchases;
	}
	
	public Purchases findOne(int id) {
		Purchases purchases= purchasesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found, Id: "+ id));
		return purchases;
	}
	
	public Purchases addPurchases(Purchases purchases) {
		return purchasesRepository.save(purchases);
	}
	
	public Purchases updatePurchases(Purchases purchases) {
		purchasesRepository.findById(purchases.getId()).orElseThrow(() -> new ResourceNotFoundException("Purchase not found, Id: "+ purchases.getId()));
		return purchasesRepository.save(purchases);
	}
	
	public void deletePurchases(int id) {
		purchasesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found, Id: "+ id));
		purchasesRepository.deleteById(id);
	}
}
