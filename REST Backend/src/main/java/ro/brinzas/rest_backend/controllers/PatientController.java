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

import ro.brinzas.rest_backend.dto.PatientDTO;
import ro.brinzas.rest_backend.services.PatientService;

@RestController
@CrossOrigin
@RequestMapping(path = "/patient")
public class PatientController {

	private final PatientService patientService;

	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping
	public List<PatientDTO> findAll() {
		return patientService.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public PatientDTO findById(@PathVariable("id") long id) {
		return patientService.findById(id);
	}
	
	@PostMapping
	public PatientDTO insert(@RequestBody PatientDTO patientToInsert) {
		return patientService.insert(patientToInsert);
	}
	
	@PutMapping
	public PatientDTO update(@RequestBody PatientDTO patientToUpdate) {
		return patientService.update(patientToUpdate);
	}
	
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") long id) {
		patientService.deleteById(id);
	}
	
}
