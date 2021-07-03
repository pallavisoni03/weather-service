package com.travel.weatherservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class WeatherData {
	
	private @Id Long id;
	private String date; 
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
