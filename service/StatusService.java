package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Status;
import com.example.demo.repository.StatusRepository;

@Service
public class StatusService {
	@Autowired
	private StatusRepository statusRepository;
	
	public List<Status> getAllStatuses(){
		List<Status> statuses = new ArrayList<>();
		statusRepository.findAll().forEach(statuses::add);
		return statuses;
	}
	
	public Status findOne(int id) {
		Status status= statusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Status not found, Id: "+ id)); 
		return status;
	}
	
	public Status addStatus(Status status) throws BadRequestException, AlreadyExistsException {
		if(status.getName().trim().isEmpty()) {
			throw new BadRequestException("Status name cannot be empty!");
		}
		if(statusRepository.existsByName(status.getName())) {
			throw new AlreadyExistsException("Status with name \"" + status.getName() + "\" already exists!");
		}
		return statusRepository.save(status);
	}
	
	public Status updateStatus(Status status) throws BadRequestException {
		statusRepository.findById(status.getId()).orElseThrow(() -> new ResourceNotFoundException("Status not found, Id: "+ status.getId())); 
		if(status.getName().trim().isEmpty()) {
			throw new BadRequestException("Status name cannot be empty!");
		}
		return statusRepository.save(status);
	}
	
	public void deleteStatus(int id) {
		statusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Status not found, Id: "+ id)); 
		statusRepository.deleteById(id);
	}
}
