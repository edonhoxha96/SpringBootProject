package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import javax.persistence.JoinColumn;

@Entity
public class GlassBodyVariant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	private Product product;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BODYVARIANT_ID", referencedColumnName = "ID")
	private BodyVariant bodyVariant;
	
	public GlassBodyVariant() {}
	
	public GlassBodyVariant(Product product, BodyVariant bodyVariant) {
		super();
		this.product = product;
		this.bodyVariant = bodyVariant;
	}

	public int getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BodyVariant getBodyVariant() {
		return bodyVariant;
	}

	public void setBodyVariant(BodyVariant bodyVariant) {
		this.bodyVariant = bodyVariant;
	}
	
	
	
}
