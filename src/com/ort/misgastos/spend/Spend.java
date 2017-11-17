package com.ort.misgastos.spend;

import java.util.Date;

public class Spend {
	private int id;
	private String description;
	private Category category;
	private float value;
	private Date created;
	
	public Spend(int id, String description, Category category, float value, Date created) {
		super();
		
		this.id = id;
		this.description = description;
		this.category = category;
		this.value = value;
		this.created = created;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getCreated() {
		return created;
	}
}
