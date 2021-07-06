package com.travel.weather.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class LocationDataEntity {
	
	private @Id @GeneratedValue Long id;
	
	@OneToOne
	@JoinColumn(name = "weatherDataEntity_id", referencedColumnName = "id")
	//@OneToOne(cascade = CascadeType.ALL)
	private WeatherDataEntity weatherDataEntity;
	
	private String lat;
	private String lon;
	private String city;
	private String state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
