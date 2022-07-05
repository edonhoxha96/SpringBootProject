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
public class Depot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String address;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "city_id",referencedColumnName = "id")
	private City city;
	@OneToMany(mappedBy = "depot")
	private Set<Purchases> purchases;
	@OneToMany(mappedBy = "depot")
	private Set<Stock> stocks;
	
	public Depot() {}
	public Depot(String name, String address, City city) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}
