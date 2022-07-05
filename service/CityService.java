package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> getAllCitys(){
		List<City> cities = new ArrayList<>();
		cityRepository.findAll().forEach(cities::add);
		return cities;
	}
	
	public City findOne(int id) {
		City city= cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found, Id: "+ id));
		return city;
	}
	
	public City addCity(City city) throws BadRequestException, AlreadyExistsException {
		if(city.getName().trim().isEmpty()) {
			throw new BadRequestException("City name cannot be empty!");
		}
		if(cityRepository.existsByNameAndCountryId(city.getName(), city.getCountry().getId())) {
			throw new AlreadyExistsException("City with name \"" + city.getName() + "\" already exists!");
		}
		return cityRepository.save(city);
	}
	
	public City updateCity(City city) throws BadRequestException {
		cityRepository.findById(city.getId()).orElseThrow(() -> new ResourceNotFoundException("City not found, Id: "+ city.getId()));
		if(city.getName().trim().isEmpty()) {
			throw new BadRequestException("City name cannot be empty!");
		}
		return cityRepository.save(city);
	}
	
	public void deleteCity(int id) {
		cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found, Id: "+ id));
		cityRepository.deleteById(id);
	}
}
