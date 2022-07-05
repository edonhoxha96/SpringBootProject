package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	
	public List<Supplier> getAllSuppliers(){
		List<Supplier> suppliers = new ArrayList<>();
		supplierRepository.findAll().forEach(suppliers::add);
		return suppliers;
	}
	
	public Supplier findOne(int id) {
		Supplier supplier= supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found, Id: "+ id)); 
		return supplier;
	}
	
	public Supplier addSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
	public Supplier updateSupplier(Supplier supplier) {
		supplierRepository.findById(supplier.getId()).orElseThrow(() -> new ResourceNotFoundException("Supplier not found, Id: "+ supplier.getId()));
		return supplierRepository.save(supplier);
	}
	
	public void deleteSupplier(int id) {
		supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found, Id: "+ id));
		supplierRepository.deleteById(id);
	}
}
