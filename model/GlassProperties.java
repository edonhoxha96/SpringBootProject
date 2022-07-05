package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import javax.persistence.JoinColumn;

@Entity
public class GlassProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	private Product product;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "PROPERTIES_ID", referencedColumnName = "ID")
	private Properties properties;
	
	public GlassProperties() {}
	
	public GlassProperties(Product product, Properties properties) {
		this.product = product;
		this.properties = properties;
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

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	
	
}
