package com.travel.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.travel.weather.entity.WeatherDataEntity;

/**
 * This provides interface to persist and query java objects for weather data into the database
 */
public interface WeatherRepository extends JpaRepository<WeatherDataEntity, Long>{
	
	/**
	  * This implementation provides the data filtered by specific date
	  */
	List<WeatherDataEntity> findAllByDate(String date);
	
}
