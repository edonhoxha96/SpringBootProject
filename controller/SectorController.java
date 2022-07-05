package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Sector;
import com.example.demo.service.SectorService;

@RestController
public class SectorController {
	@Autowired
	private SectorService sectorService;
	
	@GetMapping("/sectors")
	public List<Sector> getAllSectors(){
		return sectorService.getAllSectors();
	}
	
	@GetMapping("/sectors/{id}")
	public ResponseEntity<Sector> findSector(@PathVariable int id) {
		Sector sector = sectorService.findOne(id);
		return new ResponseEntity<>(sector, HttpStatus.OK);
	}
	
	@PostMapping("/add-sector")
	public ResponseEntity<Sector> addSector(@RequestBody Sector sector) throws BadRequestException, AlreadyExistsException {
		Sector s = sectorService.addSector(sector);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-sector")
	public ResponseEntity<Sector> updateSector(@RequestBody Sector sector) throws BadRequestException {
		Sector s = sectorService.updateSector(sector);
		return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/sectors/delete/{id}")
	public ResponseEntity<Sector> deleteSector(@PathVariable int id) {
		sectorService.deleteSector(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
