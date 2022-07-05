package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import javax.persistence.JoinColumn;

@Entity
public class ProductSupplier{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String kontId;
	@NotNull
	private double buyingPrice;
	@NotNull
	private double sellingPrice;
	@Column(nullable = true)
	private int totalQuantity;
	@Column(nullable = true)
	private int minQuantity;
	@ManyToOne(optional = false)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	private Product product;
	@ManyToOne(optional = false)
	@JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "ID")
	private Supplier supplier;
	
	public ProductSupplier() {}
	
	public ProductSupplier(String kontId,Product product, Supplier supplier, double buyingPrice, double sellingPrice, int minQuantity) {
		this.kontId = kontId;
		this.product = product;
		this.supplier = supplier;
		this.buyingPrice = buyingPrice;
		this.sellingPrice = sellingPrice;
		this.totalQuantity = 0;
		this.minQuantity = minQuantity;
	}

	public int getId() {
		return id;
	}

	public String getKontId() {
		return kontId;
	}

	public void setKontId(String kontId) {
		this.kontId = kontId;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
	public int getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}	
	
}
