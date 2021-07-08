package com.travel.weather.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity

/* 
 * This entity represents the weather data table in database
 */
public class WeatherDataEntity {
	
	private @Id long id;
	private String date; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private LocationDataEntity location;

	@ElementCollection
	private List<Float> temperature;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public List<Float> getTemperature() {
		return temperature;
	}
	public void setTemperature(List<Float> temperature) {
		this.temperature = temperature;
	}
	
	

}
