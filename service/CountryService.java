package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getAllCountrys(){
		List<Country> countries = new ArrayList<>();
		countryRepository.findAll().forEach(countries::add);
		return countries;
	}
	
	public Country findOne(int id) {
		Country country= countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country not found, Id: "+ id));
		return country;
	}
	
	public Country addCountry(Country country) throws BadRequestException, AlreadyExistsException {
		if(country.getName().trim().isEmpty()) {
			throw new BadRequestException("Country name cannot be empty!");
		}
		if(countryRepository.existsByName(country.getName())) {
			throw new AlreadyExistsException("Country with name \"" + country.getName() + "\" already exists!");
		}
		return countryRepository.save(country);
	}
	
	public Country updateCountry(Country country) throws BadRequestException{
		countryRepository.findById(country.getId()).orElseThrow(() -> new ResourceNotFoundException("Country not found, Id: "+ country.getId()));
		if(country.getName().trim().isEmpty()) {
			throw new BadRequestException("Country name cannot be empty!");
		}
		return countryRepository.save(country);
	}
	
	public void deleteCountry(int id) {
		countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country not found, Id: "+ id));
		countryRepository.deleteById(id);
	}
}
