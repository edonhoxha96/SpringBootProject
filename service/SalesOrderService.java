package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Sales;
import com.example.demo.model.SalesOrder;
import com.example.demo.repository.SalesOrderRepository;


@Service
public class SalesOrderService {
	@Autowired
	private SalesOrderRepository salesOrderRepository;

	@Autowired
	private SalesService salesService;
	
	public List<SalesOrder> getAllSalesOrders(){
		List<SalesOrder> salesOrders = new ArrayList<>();
		salesOrderRepository.findAll().forEach(salesOrders::add);
		return salesOrders;
	}
	
	public SalesOrder findOne(int id) {
		SalesOrder salesOrder= salesOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SalesOrder not found, Id: "+ id));
		return salesOrder;
	}
	
	public SalesOrder addSalesOrder(SalesOrder salesOrder) {
		return salesOrderRepository.save(salesOrder);
	}
	
	public SalesOrder updateSalesOrder(SalesOrder salesOrder) {
		salesOrderRepository.findById(salesOrder.getId()).orElseThrow(() -> new ResourceNotFoundException("SalesOrder not found, Id: "+ salesOrder.getId()));
		return salesOrderRepository.save(salesOrder);
	}
	
	public void deleteSalesOrder(int id) {
		SalesOrder so = salesOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SalesOrder not found, Id: "+ id));
		Sales s = new Sales(so.getSellingPrice(), null, null, so.getQuantity(), so.getStock());
		salesService.addSales(s);
		
		salesOrderRepository.deleteById(so.getId());
	}
	
	public void cancelSalesOrder(int id) {
		salesOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SalesOrder not found, Id: "+ id));
		salesOrderRepository.deleteById(id);
	}
}
