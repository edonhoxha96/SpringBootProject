package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;

@Entity
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp 
	private LocalDateTime updated;
	private String description;
	@ManyToOne(optional = false)
	@JoinColumn(name = "city_id",referencedColumnName = "id")
	private City city;

	@OneToMany(mappedBy = "supplier")
	private Set<PurchasesOrder> purchasesOrders;
	@OneToMany(mappedBy = "supplier")
	private Set<Purchases> purchases;
//	@OneToMany(mappedBy = "supplier")
//	private Set<Stock> stocks;
	@OneToMany(mappedBy = "supplier")
	private Set<ProductSupplier> productSupplier;
	public Supplier() {}
	public Supplier(String firstName, String lastName, String phone, String address, LocalDateTime created,
			LocalDateTime updated, String description, City city, Set<ProductSupplier> productSupplier) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.created = created;
		this.updated = updated;
		this.description = description;
		this.city = city;
		this.productSupplier = productSupplier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
}
