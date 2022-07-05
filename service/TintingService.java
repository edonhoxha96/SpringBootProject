package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Tinting;
import com.example.demo.repository.TintingRepository; 
import java.util.List;
import java.util.ArrayList;  

@Service
public class TintingService {
	@Autowired
	private TintingRepository tintingRepository;
	
	public List<Tinting> getAllTintings(){
		List<Tinting> tinting = new ArrayList<>();
		tintingRepository.findAll().forEach(tinting::add);
		return tinting;
	}
	
	public Tinting findOne(int id) {
		Tinting tinting= tintingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tinting not found, Id: "+ id));
		return tinting;
	}
	
	public Tinting addTinting(Tinting tinting) throws BadRequestException, AlreadyExistsException {
		if(tinting.getName().trim().isEmpty()) {
			throw new BadRequestException("Tinting name cannot be empty!");
		}
		if(tintingRepository.existsByName(tinting.getName())) {
			throw new AlreadyExistsException("Tinting with name \"" + tinting.getName() + "\" already exists!");
		}
		return tintingRepository.save(tinting);
	}
	
	public Tinting updateTinting(Tinting tinting) throws BadRequestException {
		tintingRepository.findById(tinting.getId()).orElseThrow(() -> new ResourceNotFoundException("Tinting not found, Id: "+ tinting.getId()));
		if(tinting.getName().trim().isEmpty()) {
			throw new BadRequestException("Tinting name cannot be empty!");
		}
		return tintingRepository.save(tinting);
	}
	
	public void deleteTinting(int id) {
		tintingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tinting not found, Id: "+ id));
		tintingRepository.deleteById(id);
	}
}
