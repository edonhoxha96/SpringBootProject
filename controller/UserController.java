package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;    
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;    
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.UserRecord;
import com.example.demo.service.UserService;

import java.util.List;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/")
	public List<UserRecord> getAllUser(){
		return userService.getAllUsers();
	}
	@GetMapping("/listusers")
	public ModelAndView listUsers(Model model) {
		ModelAndView modelAndView = new ModelAndView();    
		modelAndView.setViewName("user-data");        
		modelAndView.addObject("users", userService.getAllUsers());
		return modelAndView;
	}
	
	@GetMapping("/users/{id}")
	public UserRecord findUser(@PathVariable int id) {
		UserRecord userRecord = userService.findOne(id);
		System.out.println("test: " + userRecord);
		if(userRecord == null)
			throw new UserNotFoundException("id"+ id);
		return userRecord;
	}
	
	@RequestMapping(value="/add-user", method=RequestMethod.POST)
	public void addUser(@RequestBody UserRecord userRecord) {
		userService.addUser(userRecord);
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
	
}
