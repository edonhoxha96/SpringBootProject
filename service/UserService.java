package com.example.demo.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.UserRecord;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository; 

@Service @Transactional
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	public List<UserRecord> getAllUsers(){
		List<UserRecord> userRecords = new ArrayList<>();
		userRepository.findAll().forEach(userRecords::add);
		return userRecords;
	}
	
	public UserRecord findOne(int id) {
		UserRecord user= userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found")); 
		return user;
	}
	
	public Role findRoleById(int id) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Role with id " + id + " was not found")); 
		return role;
	}
	
	public UserRecord addUser(UserRecord userRecord) {
		return userRepository.save(userRecord);
	}
	
	public UserRecord updateUser(UserRecord userRecord) {
		userRepository.findById(userRecord.getId()).orElseThrow(() -> new UserNotFoundException("User with id " + userRecord.getId() + " was not found"));
		return userRepository.save(userRecord);
	}
	
	public Role addRole(Role role) {
		roleRepository.findById(role.getId()).orElseThrow(() -> new UserNotFoundException("Role with id " + role.getId() + " was not found"));
		return roleRepository.save(role);
	}
	
	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}
	
	public void addRoleToUser(String username, String roleName) {
		UserRecord user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with id " + username + " was not found"));
		Role role = roleRepository.findByName(roleName).orElseThrow(() -> new UserNotFoundException("Role with id " + roleName + " was not found"));;
		user.getRoles().add(role);
	}
	
	
	public void deleteUser(int id) {
		userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found")); 
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserRecord user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User "+ username +" not found"));
		return UserDetailsImpl.build(user);
	}
}
