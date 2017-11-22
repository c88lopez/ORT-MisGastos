package com.ort.misgastos.spend;

import java.util.Date;

public class Spend {
	private long id;
	private String description;
	private Category category;
	private float value;
	private String created;
	
	public Spend(int id, String description, Category category, float value, String created) {
		super();
		
		this.id = id;
		this.description = description;
		this.category = category;
		this.value = value;
		this.created = created;
	}
	
	public Spend(Category category, float value, String description) {
		super();
		
		this.category = category;
		this.value = value;
		this.description = description;
	}
	
	public Spend(Category category, float value, String description, String created) {
		super();
		
		this.category = category;
		this.value = value;
		this.description = description;
		this.created = created;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
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
	
	public void setCreated(String created) {
		this.created = created;
	}
	
	public String getCreated() {
		return created;
	}
}
