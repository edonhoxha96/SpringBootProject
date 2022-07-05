package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Properties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@OneToMany(mappedBy = "properties")
	private Set<GlassProperties> glassProperties;
	public Properties() {}
	
	public Properties(String name, Set<GlassProperties> glassProperties) {
		super();
		this.name = name;
		this.glassProperties = glassProperties;
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

//	public Set<GlassProperties> getGlassProperties() {
//		return glassProperties;
//	}

	public void setProduct(Set<GlassProperties> glassProperties) {
		this.glassProperties = glassProperties;
	}

	@Override
	public String toString() {
		return "Properties [name=" + name + "]";
	}
	
}
