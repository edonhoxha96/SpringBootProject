package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class BodyVariant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@OneToMany(mappedBy = "bodyVariant")
	private Set<GlassBodyVariant> glassBodyVariant;
	
	public BodyVariant() {}
	public BodyVariant(String name, Set<GlassBodyVariant> glassBodyVariant) {
		super();
		this.name = name;
		this.glassBodyVariant = glassBodyVariant;
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

//	public Set<GlassBodyVariant> getGlassBodyVariant() {
//		return glassBodyVariant;
//	}

	public void setProduct(Set<GlassBodyVariant> glassBodyVariant) {
		this.glassBodyVariant = glassBodyVariant;
	}
	
	
}
