package com.example.demo.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
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
public class SalesOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	@Column(nullable = true) //ndryshohet
	private double sellingPrice;
	@NotNull
	private int quantity;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp 
	private LocalDateTime updated;
	@ManyToOne(optional = true) // bohet false
	@JoinColumn(name = "stock_id", referencedColumnName = "id")
	private Stock stock;
	@ManyToOne(optional = true)
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private City city;
	public SalesOrder() {}
	
	public SalesOrder(int id, String description, double sellingPrice, int quantity, LocalDateTime created, LocalDateTime updated,
			Stock stock, City city) {
		super();
		this.id = id;
		this.description = description;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
		this.created = created;
		this.updated = updated;
		this.stock = stock;
		this.city = city;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public City getCity() {
		return city;
	}
	public void setStatus(City city) {
		this.city = city;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
	
}
