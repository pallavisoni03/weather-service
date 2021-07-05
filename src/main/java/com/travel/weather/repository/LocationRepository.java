package com.travel.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.weather.entity.LocationDataEntity;

public interface LocationRepository extends JpaRepository<LocationDataEntity, Long>{
	
}
