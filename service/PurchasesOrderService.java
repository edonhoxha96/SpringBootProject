package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.PurchasesOrder;
import com.example.demo.repository.PurchasesOrderRepository;

@Service
public class PurchasesOrderService {
	@Autowired
	private PurchasesOrderRepository purchasesOrderRepository;
	
	public List<PurchasesOrder> getAllPurchasesOrders(){
		List<PurchasesOrder> purchasesOrders = new ArrayList<>();
		purchasesOrderRepository.findAll().forEach(purchasesOrders::add);
		return purchasesOrders;
	}
	
	public PurchasesOrder findOne(int id) {
		PurchasesOrder purchasesOrder= purchasesOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found, Id: "+ id));
		return purchasesOrder;
	}
	
	public PurchasesOrder addPurchasesOrder(PurchasesOrder purchasesOrder) {
		return purchasesOrderRepository.save(purchasesOrder);
	}
	
	public PurchasesOrder updatePurchasesOrder(PurchasesOrder purchasesOrder) {
		purchasesOrderRepository.findById(purchasesOrder.getId()).orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found, Id: "+ purchasesOrder.getId()));
		return purchasesOrderRepository.save(purchasesOrder);
	}
	
	public void deletePurchasesOrder(int id) {
		purchasesOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found, Id: "+ id));
		purchasesOrderRepository.deleteById(id);
	}
}
