package com.travel.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.travel.weather.entity.WeatherData;

public interface WeatherRepository extends JpaRepository<WeatherData, Long>{
	
	List<WeatherData> findAllByDate(String date);
	
}
