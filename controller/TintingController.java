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

import com.example.demo.service.TintingService;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Tinting;

@RestController
public class TintingController {
	@Autowired
	private TintingService tintingService;
	
	@GetMapping("/tintings")
	public List<Tinting> getAllTintings(){
		return tintingService.getAllTintings();
	}
	
	@GetMapping("/tintings/{id}")
	public ResponseEntity<Tinting> findTinting(@PathVariable int id) {
		Tinting tinting = tintingService.findOne(id);
		return new ResponseEntity<Tinting>(tinting, HttpStatus.OK);
	}
	
	@PostMapping("add-tinting")
	public ResponseEntity<Tinting> addTinting(@RequestBody Tinting tinting) throws BadRequestException, AlreadyExistsException {
		Tinting t = tintingService.addTinting(tinting);
		return new ResponseEntity<Tinting>(t, HttpStatus.CREATED);
	}
	
	@PutMapping("update-tinting")
	public ResponseEntity<Tinting> updateTinting(@RequestBody Tinting tinting) throws BadRequestException {
		Tinting t = tintingService.updateTinting(tinting);
		return new ResponseEntity<Tinting>(t, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/tintings/delete/{id}")
	public ResponseEntity<Tinting> deleteTinting(@PathVariable int id) {
		tintingService.deleteTinting(id);
		return new ResponseEntity<Tinting>(HttpStatus.OK);
	}
}
