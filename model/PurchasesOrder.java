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

@Entity
public class PurchasesOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	@Column(nullable = true)
	private int quantity;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp 
	private LocalDateTime updated;
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	@ManyToOne(optional = false)
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private Supplier supplier;
	@ManyToOne(optional = false)
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	private Status status;
	public PurchasesOrder() {}
	public PurchasesOrder(int id, String description, int quantity, LocalDateTime created, LocalDateTime updated,
			Product product, Supplier supplier, Status status) {
		super();
		this.id = id;
		this.description = description;
		this.quantity = quantity;
		this.created = created;
		this.updated = updated;
		this.product = product;
		this.supplier = supplier;
		this.status = status;
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
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	
}
