package com.ort.misgastos.entities;

public class Report {

	private Category category;
	private float spend;
	private float percent;
		
	public Report(Category category, float spend, float percent) {
		super();
		
		this.category = category;
		this.spend = spend;
		this.percent = percent;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Category getCategoria() {
		return category;
	}
	
	public void setSpend(float spend) {
		this.spend = spend;
	}
	
	public float getSpend() {
		return spend;
	}
	
	public void setPercent(float percent) {
		this.percent = percent;
	}
	
	public float getPercent() {
		return percent;
	}
}
