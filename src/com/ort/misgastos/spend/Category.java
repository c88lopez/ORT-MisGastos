package com.ort.misgastos.spend;

public class Category {
	private long id;
	private String name;
	
	public Category(long id, String name) {
		super();
		
		this.id = id;
		this.name = name;
	}
	
	public Category(String name) {
		super();
		
		this.name = name;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
