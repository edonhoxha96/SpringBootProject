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
public class City {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;
	@OneToMany(mappedBy = "city")
	private Set<Costumer> costumers;
	@OneToMany(mappedBy = "city")
	private Set<Supplier> suppliers;
	@OneToMany(mappedBy = "city")
	private Set<Depot> depots;
	@OneToMany(mappedBy = "city")
	private Set<SalesOrder> salesOrders;
	
	public City() {}
	public City(int id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
