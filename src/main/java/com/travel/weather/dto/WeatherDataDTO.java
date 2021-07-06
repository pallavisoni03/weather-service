package com.travel.weather.dto;

import java.util.List;

public class WeatherDataDTO {

	private Long id;
	private String date;
	private LocationDataDTO location;
	
	private List<Float> temperature;
	
	
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
	public LocationDataDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDataDTO location) {
		this.location = location;
	}
	public List<Float> getTemperature() {
		return temperature;
	}
	public void setTemperature(List<Float> temperature) {
		this.temperature = temperature;
	}
	
	
}
