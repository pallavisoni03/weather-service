package com.travel.weather.dto;

import com.travel.weather.entity.LocationDataEntity;

public class WeatherDataDTO {

	private Long id;
	private String date;
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
