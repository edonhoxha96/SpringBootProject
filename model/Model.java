package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BRAND_ID", referencedColumnName = "ID")
	private Brand brand;
	@OneToMany(mappedBy = "model")
	private Set<Product> product;
	
	public Model() {}
	
	public Model(String name, Brand brand,Set<Product> product) {
		this.name = name;
		this.brand = brand;
		this.product = product;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
//
//	public Set<GlassModelYear> getGlassModelYear() {
//		return glassModelYear;
//	}
//
//	public void setGlassModelYear(Set<GlassModelYear> glassModelYear) {
//		this.glassModelYear = glassModelYear;
//	}

	@Override
	public String toString() {
		return "Model [name=" + name + ", brand=" + brand + "]";
	}
	
}
