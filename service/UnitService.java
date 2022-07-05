package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Unit;
import com.example.demo.repository.UnitRepository;

@Service
public class UnitService {
	@Autowired
	private UnitRepository unitRepository;
	
	public List<Unit> getAllUnits(){
		List<Unit> units = new ArrayList<>();
		unitRepository.findAll().forEach(units::add);
		return units;
	}
	
	public Unit findOne(int id) {
		Unit unit= unitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unit not found, Id: "+ id));
		return unit;
	}
	
	public Unit addUnit(Unit unit) {
		return unitRepository.save(unit);
	}
	
	public Unit updateUnit(Unit unit) {
		unitRepository.findById(unit.getId()).orElseThrow(() -> new ResourceNotFoundException("Unit not found, Id: "+ unit.getId()));
		return unitRepository.save(unit);
	}
	
	public void deleteUnit(int id) {
		unitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unit not found, Id: "+ id));
		unitRepository.deleteById(id);
	}
}
