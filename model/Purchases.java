package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;

@Entity
public class Purchases {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double buyingPrice;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp 
	private LocalDateTime updated;
	@NotNull
	private int quantity;
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	@ManyToOne(optional = false)
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private Supplier supplier;
	@ManyToOne(optional = false)
	@JoinColumn(name = "depot_id", referencedColumnName = "id")
	private Depot depot;
	public Purchases() {}
	public Purchases(double buyingPrice, LocalDateTime created, LocalDateTime updated, int quantity, Product product,
			Supplier supplier, Depot depot) {
		super();
		this.buyingPrice = buyingPrice;
		this.created = created;
		this.updated = updated;
		this.quantity = quantity;
		this.product = product;
		this.supplier = supplier;
		this.depot = depot;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	
}	