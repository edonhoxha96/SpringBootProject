package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Sector;
import com.example.demo.repository.SectorRepository;

@Service
public class SectorService {
	@Autowired
	private SectorRepository sectorRepository;
	
	public List<Sector> getAllSectors(){
		List<Sector> sectors = new ArrayList<>();
		sectorRepository.findAll().forEach(sectors::add);
		return sectors;
	}
	
	public Sector findOne(int id) {
		Sector sector= sectorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sector not found, Id: "+ id));
		return sector;
	}
	
	public Sector addSector(Sector sector) throws BadRequestException, AlreadyExistsException {
		if(sector.getName().trim().isEmpty()) {
			throw new BadRequestException("Sector name cannot be empty!");
		}
		if(sectorRepository.existsByName(sector.getName())) {
			throw new AlreadyExistsException("Sector with name \"" + sector.getName() + "\" already exists!");
		}
		return sectorRepository.save(sector);
	}
	
	public Sector updateSector(Sector sector) throws BadRequestException {
		sectorRepository.findById(sector.getId()).orElseThrow(() -> new ResourceNotFoundException("Sector not found, Id: "+ sector.getId()));
		if(sector.getName().trim().isEmpty()) {
			throw new BadRequestException("Sector name cannot be empty!");
		}
		return sectorRepository.save(sector);
	}
	
	public void deleteSector(int id) {
		sectorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sector not found, Id: "+ id));
		sectorRepository.deleteById(id);
	}
}
