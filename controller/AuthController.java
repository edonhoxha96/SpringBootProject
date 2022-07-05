package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.UserRecord;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.UserDetailsImpl;

import payload.JwtResponse;
import payload.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired 
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/singin")
	public ResponseEntity<?> authenticateUser(@RequestBody UserRecord user){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserRecord user){
		System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEEST");
		if(userRepository.existsByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username already exists!"));
		}
		List<Role> roles= new ArrayList<>();
		user.getRoles().forEach(r -> {
			Role role = roleRepository.findByName(r.getName()).orElseThrow(() -> new ResourceNotFoundException("Role " + r.getName() + " not found"));
			roles.add(role);
		});
			
		UserRecord newUser = new UserRecord(user.getUsername(), encoder.encode(user.getPassword()), roles);
	
		userRepository.save(newUser);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
