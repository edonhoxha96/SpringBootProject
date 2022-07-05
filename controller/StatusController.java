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
import com.example.demo.model.Status;
import com.example.demo.service.StatusService;

@RestController
public class StatusController {
	@Autowired
	private StatusService statusService;
	
	@GetMapping("/statuses")
	public ResponseEntity<List<Status>> getAllStatuss(){
		List<Status> statuses = statusService.getAllStatuses();
		return new ResponseEntity<>(statuses, HttpStatus.OK);
	}
	
	@GetMapping("/statuses/{id}")
	public ResponseEntity<Status> findStatus(@PathVariable int id) {
		Status status = statusService.findOne(id);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@PostMapping("/add-status")
	public ResponseEntity<Status> addStatus(@RequestBody Status status) throws BadRequestException, AlreadyExistsException {
		Status newStatus = statusService.addStatus(status);
		return new ResponseEntity<>(newStatus, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-status")
	public ResponseEntity<Status> updateStatus(@RequestBody Status status) throws BadRequestException {
		Status newStatus = statusService.updateStatus(status);
		return new ResponseEntity<>(newStatus, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/statuses/delete/{id}")
	public ResponseEntity<Status> deleteStatus(@PathVariable int id) {
		statusService.deleteStatus(id);
		return new ResponseEntity<Status>(HttpStatus.OK);
	}
}
