package com.travel.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.weather.entity.LocationDataEntity;

/**
 * This provides interface to persist and query java objects for location data into the database
 */
public interface LocationRepository extends JpaRepository<LocationDataEntity, Long>{
	
}
