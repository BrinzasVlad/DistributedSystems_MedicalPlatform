package ro.brinzas.rest_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.brinzas.rest_backend.dto.CaregiverDTO;
import ro.brinzas.rest_backend.dto.CaregiverNoPatientsDTO;
import ro.brinzas.rest_backend.services.CaregiverService;

@RestController
@CrossOrigin
@RequestMapping(path = "/caregiver")
public class CaregiverController {
	
	private final CaregiverService caregiverService;

	@Autowired
	public CaregiverController(CaregiverService caregiverService) {
		this.caregiverService = caregiverService;
	}
	
	@GetMapping
	public List<CaregiverNoPatientsDTO> findAllNoPatients() {
		return caregiverService.findAllNoPatients();
	}
	
	@GetMapping(path = "/{id}")
	public CaregiverDTO findById(@PathVariable("id") long id) {
		return caregiverService.findById(id);
	}
	
	@PostMapping
	public CaregiverDTO insert(@RequestBody CaregiverDTO caregiverToInsert) {
		return caregiverService.insert(caregiverToInsert);
	}
	
	@PutMapping
	public CaregiverDTO update(@RequestBody CaregiverDTO caregiverToUpdate) {
		return caregiverService.update(caregiverToUpdate);
	}
	
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") long id) {
		caregiverService.deleteById(id);
	}
}
