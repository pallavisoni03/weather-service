package com.travel.weatherservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.weatherservice.entity.WeatherData;

public interface WeatherRepository extends JpaRepository<WeatherData, Long>{

}
