package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class GlassModelYear {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	private Product product;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "MODEL_ID", referencedColumnName = "ID")
	private Model model;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "PRODUCTIONYEAR_ID", referencedColumnName = "ID")
	private ProductionYear productionYear;

	public GlassModelYear() {}
	
	public GlassModelYear(Product product, Model model, ProductionYear productionYear) {
		this.product = product;
		this.model = model;
		this.productionYear = productionYear;
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public ProductionYear getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(ProductionYear productionYear) {
		this.productionYear = productionYear;
	}
	
}
