package com.travel.weather.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity

public class WeatherDataEntity {
	
	private @Id Long id;
	private String date; 
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "weatherDataEntity")
	private LocationDataEntity location;
	
	
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
	public LocationDataEntity getLocation() {
		return location;
	}
	public void setLocation(LocationDataEntity location) {
		this.location = location;
	}
	

}
