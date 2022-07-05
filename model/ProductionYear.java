package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class ProductionYear {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@OneToMany(mappedBy = "productionYear")
	private Set<GlassModelYear> glassModelYear;

	public ProductionYear() {}
	
	public ProductionYear(String name, Set<GlassModelYear> glassModelYear) {
		this.name = name;
		this.glassModelYear = glassModelYear;
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

//	public Set<GlassModelYear> getGlassModelYear() {
//		return glassModelYear;
//	}

	public void setGlassModelYear(Set<GlassModelYear> glassModelYear) {
		this.glassModelYear = glassModelYear;
	}

	@Override
	public String toString() {
		return "ProductionYear [name=" + name + "]";
	}
	
	
}
