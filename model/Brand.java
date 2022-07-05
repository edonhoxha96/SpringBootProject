package com.example.demo.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@OneToMany(mappedBy = "brand")
	private Set<Model> model;
	
	public Brand() {}
	
	public Brand(String name, Set<Model> model) {
		this.name = name;
		this.model = model;
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

	public void setModel(Set<Model> model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Brand [name=" + name + "]";
	}
	
}
