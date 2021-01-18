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

import ro.brinzas.rest_backend.dto.MedicationDTO;
import ro.brinzas.rest_backend.services.MedicationService;

@RestController
@CrossOrigin
@RequestMapping(path = "/medication")
public class MedicationController {
	
	private final MedicationService medicationService;

	@Autowired
	public MedicationController(MedicationService medicationService) {
		this.medicationService = medicationService;
	}
	
	@GetMapping
	public List<MedicationDTO> findAll() {
		return medicationService.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public MedicationDTO findById(@PathVariable("id") long id) {
		return medicationService.findById(id);
	}
	
	@PostMapping
	public MedicationDTO insert(@RequestBody MedicationDTO medicationToInsert) {
		return medicationService.insert(medicationToInsert);
	}
	
	@PutMapping
	public MedicationDTO update(@RequestBody MedicationDTO medicationToUpdate) {
		return medicationService.update(medicationToUpdate);
	}
	
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") long id) {
		medicationService.deleteById(id);
	}

}
