package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Depot;
import com.example.demo.model.ProductSupplier;
import com.example.demo.model.Sector;
//import com.example.demo.model.Depot;
//import com.example.demo.model.Product;
//import com.example.demo.model.Sector;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private ProductSupplierService productSupplierService;
	
	public List<Stock> getAllStocks(){
		List<Stock> stocks = new ArrayList<>();
		stockRepository.findAll().forEach(stocks::add);
		return stocks;
	}
	
	public List<Stock> getStockByProduct(int id){
		List<Stock> stocks = new ArrayList<>();
		stockRepository.findAll().forEach(s -> {
			if(s.getProductSupplier().getProduct().getId() == id) {
				stocks.add(s);
			}
		});
		return stocks;
	}
	
	public Stock findOne(int id) {
		Stock stock= stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found, Id: "+ id));
		return stock;
	}
	
	public List<Stock> findAllBySector(int secId) {
		List<Stock> stocks = new ArrayList<>();
		stockRepository.findAll().forEach(s -> {
			if(s.getSector().getId() == secId) {
				stocks.add(s);
			}
		});
		
		return stocks;
	}
	
	public Stock addStock(Stock stock) throws BadRequestException{
		if(stock.getProductSupplier() == null) {
			throw new BadRequestException("Product Supplier can't be null!");
		}
		if(stock.getDepot() == null) {
			throw new BadRequestException("Depot can't be null!");
		}
		if(stock.getSector() == null) {
			throw new BadRequestException("Sector can't be null!");
		}
		ProductSupplier ps = productSupplierService.findOne(stock.getProductSupplier().getId());
		ps.setTotalQuantity(ps.getTotalQuantity() + stock.getQuantity());
		productSupplierService.updateProductSupplier(ps);
		Stock newStock = findbySector(stock.getProductSupplier().getId(), stock.getSector().getId());
		if(newStock == null) {
			return stockRepository.save(stock);			
		}else {
			newStock.setQuantity((newStock.getQuantity() + stock.getQuantity()));
			return stockRepository.save(newStock);	
		}
	}
	
	public Stock updateStock(Stock stock) {
		stockRepository.findById(stock.getId()).orElseThrow(() -> new ResourceNotFoundException("Stock not found, Id: "+ stock.getId()));
		return stockRepository.save(stock);
	}
	
	public void deleteStock(int id) {
		stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found, Id: "+ id));
		stockRepository.deleteById(id);
	}
	
	public Stock findbyProduct(int prodSupId) {
		
		List<Stock> stocks = new ArrayList<>();
		stockRepository.findAll().forEach(stocks::add);
		for(Stock s: stocks) {
			if(s.getProductSupplier().getId() == prodSupId && s.getDepot().getId() == 2) {
				return s;
			}
		}
		return null;
	}
	
	public void moveStock(Stock stock, int quantity) {
		
		Stock newStock = findbyProduct(stock.getProductSupplier().getId());
		if(newStock == null) {
			Depot d = new Depot();
			d.setId(2);
			Sector s = new Sector();
			s.setId(1005); // Id e depo 2
			newStock = new Stock(0, quantity, stock.getProductSupplier(), d, s);
			System.out.println("BackEnd- New Stock quantity: " + newStock.getQuantity());
			stockRepository.save(newStock);
		}
		else {
			newStock.setQuantity(newStock.getQuantity() + quantity);
			stockRepository.save(newStock);
		}
		
		stock.setQuantity((stock.getQuantity() - quantity));
		System.out.print(stock.getQuantity());
		if(stock.getQuantity() == 0) {
			this.deleteStock(stock.getId());
		}else {
			stockRepository.save(stock);			
		}
	}
	
	public Stock findbySector(int prodId, int secId) {
		List<Stock> stocks = new ArrayList<>();
		stockRepository.findAll().forEach(stocks::add);
		for(Stock s: stocks) {
			if(s.getProductSupplier().getId() == prodId && s.getDepot().getId() == 1 && s.getSector().getId() == secId) {
				return s;
			}
		}
		return null;
	}
	
	public Stock moveSector(Stock stock, int quantity, int secId) {
		
		Stock newStock = findbySector(stock.getProductSupplier().getId(), secId);
		if(newStock == null) {
			Sector s = new Sector();
			s.setId(secId);
			newStock = new Stock(0, quantity, stock.getProductSupplier(), stock.getDepot(), s);
			
			System.out.println("BackEnd- New Stock quantity: " + newStock.getQuantity());
			stockRepository.save(newStock);
		}
		else {
			newStock.setQuantity(newStock.getQuantity() + quantity);
			stockRepository.save(newStock);
		}
		
		System.out.println("TEST");
		stock.setQuantity((stock.getQuantity() - quantity));
		return stockRepository.save(stock);			
		
	}
	
	public double calculateAverage(int id, int quantity, double buyingPrice) {
		if(quantity < 0 || buyingPrice < 0) {
			return 0;
		}
		List<Stock> stock = stockRepository.findByProductSupplierId(id);
		if(stock.isEmpty()) {
			return 0;
		}
		double stockBuyingPrice = stock.get(0).getProductSupplier().getBuyingPrice();
		int stockQty = stock.stream().mapToInt(s -> s.getQuantity()).sum();
		return ((quantity * buyingPrice) + (stockBuyingPrice * stockQty)) / (quantity + stockQty);
	}
}
