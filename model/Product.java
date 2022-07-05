package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String eurocode;
	private String image;
	@Column(nullable = true)
	private String description;
	@ManyToOne(optional = false)
	@JoinColumn(name = "TINTING_ID", referencedColumnName = "ID")
	private Tinting tinting;
	@ManyToOne(optional = false)
	@JoinColumn(name = "GLASSTYPE_ID", referencedColumnName = "ID")
	private GlassType glassType;
	@ManyToOne
	@JoinColumn(name = "UNIT_ID", referencedColumnName = "ID")
	private Unit unit;
	@ManyToOne
	@JoinColumn(name = "TAXES_ID", referencedColumnName = "ID")
	private Taxes taxes;
	@ManyToOne(optional = false)
	@JoinColumn(name = "MODEL_ID", referencedColumnName = "ID")
	private Model model;
	@ManyToOne(optional = false)
	@JoinColumn(name = "YEARS_ID", referencedColumnName = "ID")
	private Years years;
	
	@OneToMany(mappedBy = "product")
	private Set<GlassBodyVariant> glassBodyVariant;
	
	@OneToMany(mappedBy = "product")
	private Set<GlassProperties> glassProperties;
	
	@OneToMany(mappedBy = "product")
	private Set<ProductSupplier> productSupplier;
	
	@OneToMany(mappedBy = "product")
	private Set<PurchasesOrder> purchasesOrders;
	
	@OneToMany(mappedBy = "product")
	private Set<Purchases> purchases;
	
	public Product() {}
	
	public Product(String eurocode, String image, String description, Years years, Tinting tinting, GlassType glassType, Set<GlassModelYear> glassModelYear,
			Set<GlassBodyVariant> glassBodyVariant, Set<GlassProperties> glassProperties, Set<ProductSupplier> productSupplier, Unit unit, Taxes taxes, Model model) {
		this.eurocode = eurocode;
		this.image = image;
		this.description = description;
		this.tinting = tinting;
		this.glassType = glassType;
		this.years = years;
		this.glassBodyVariant = glassBodyVariant;
		this.glassProperties = glassProperties;
		this.productSupplier = productSupplier;
		this.unit = unit;
		this.taxes = taxes;
		this.model = model;
	}
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getId() {
		return id;
	}
	public String getEurocode() {
		return eurocode;
	}
	public void setEurocode(String eurocode) {
		this.eurocode = eurocode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Years getYears() {
		return years;
	}

	public void setYears(Years years) {
		this.years = years;
	}	
	public Tinting getTinting() {
		return tinting;
	}
	public void setTinting(Tinting tinting) {
		this.tinting = tinting;
	}
	public GlassType getGlassType() {
		return glassType;
	}
	public void setGlassType(GlassType glassType) {
		this.glassType = glassType;
	}
	public void setGlassBodyVariant(Set<GlassBodyVariant> glassBodyVariant) {
		this.glassBodyVariant = glassBodyVariant;
	}
	public void setGlassProperties(Set<GlassProperties> glassProperties) {
		this.glassProperties = glassProperties;
	}

	public void setProductSupplier(Set<ProductSupplier> productSupplier) {
		this.productSupplier = productSupplier;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Taxes getTaxes() {
		return taxes;
	}

	public void setTaxes(Taxes taxes) {
		this.taxes = taxes;
	}

	@Override
	public String toString() {
		return "Product [eurocode=" + eurocode + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eurocode == null) ? 0 : eurocode.hashCode());
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (eurocode == null) {
			if (other.eurocode != null)
				return false;
		} else if (!eurocode.equals(other.eurocode))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
