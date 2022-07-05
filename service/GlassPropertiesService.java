package com.example.demo.service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.GlassProperties;
import com.example.demo.repository.GlassPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service; 
import java.util.List;
import java.util.ArrayList; 

@Service
public class GlassPropertiesService {
	@Autowired
	private GlassPropertiesRepository glassPropertiesRepository;
	
	public List<GlassProperties> getAllGlassPropertiess(){
		List<GlassProperties> glassPropertiess = new ArrayList<>();
		glassPropertiesRepository.findAll().forEach(glassPropertiess::add);
		return glassPropertiess;
	}
	
	public GlassProperties findOne(int id) {
		GlassProperties glassProperties = glassPropertiesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Glass Property not found, Id: "+ id));
		return glassProperties;
	}
	
	public List<GlassProperties> findGlassPropertiesbyProduct(int id){
		List<GlassProperties> gp = new ArrayList<>();
		glassPropertiesRepository.findAll().forEach(p -> {
			if(p.getProduct().getId() == id) {
				gp.add(p);
			}
		});
		return gp;
	}
	
	public GlassProperties addGlassProperties(GlassProperties glassProperties) {
		return glassPropertiesRepository.save(glassProperties);
	}
	
	public GlassProperties updateGlassProperties(GlassProperties glassProperties) {
		glassPropertiesRepository.findById(glassProperties.getId()).orElseThrow(() -> new ResourceNotFoundException("Glass Property not found, Id: "+ glassProperties.getId()));
		return glassPropertiesRepository.save(glassProperties);
	}
	
	public void deleteGlassProperties(int id) {
		glassPropertiesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Glass Property not found, Id: "+ id));
		glassPropertiesRepository.deleteById(id);
	}
}