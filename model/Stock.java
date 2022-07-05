package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int quantity;
	@ManyToOne(optional = false)
	@JoinColumn(name = "depot_id", referencedColumnName = "id")
	private Depot depot;
	@ManyToOne(optional = true)
	@JoinColumn(name = "sector_id", referencedColumnName = "id")
	private Sector sector;
	@ManyToOne(optional = true)
	@JoinColumn(name="product_supplier_id", referencedColumnName = "id")
	private ProductSupplier productSupplier;
	public Stock() {}
	public Stock(int id, int quantity, ProductSupplier productSupplier , Depot depot,  Sector sector) {
		this.id = id;
		this.quantity = quantity;
		this.depot = depot;
		this.sector = sector;
		this.productSupplier = productSupplier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	public ProductSupplier getProductSupplier() {
		return productSupplier;
	}
	public void setProductSupplier(ProductSupplier productSupplier) {
		this.productSupplier = productSupplier;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
}
