package com.example.demo.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Years {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@OneToMany(mappedBy = "years")
	private Set<Product> products;
	
	public Years() {}
	
	public Years(String name, Set<Product> products) {
		this.name = name;
		this.products = products;
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
	
//	public Set<Model> getModel() {
//		return model;
//	}

	public void setModel(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Years: [name=" + name + "]";
	}
	
}
