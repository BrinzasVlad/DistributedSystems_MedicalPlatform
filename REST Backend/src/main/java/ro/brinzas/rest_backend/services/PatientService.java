package ro.brinzas.rest_backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.brinzas.rest_backend.dto.PatientDTO;
import ro.brinzas.rest_backend.entities.Patient;
import ro.brinzas.rest_backend.repositories.PatientRepository;

@Service
public class PatientService {
	
	private final PatientRepository patientRepository;

	@Autowired
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public PatientDTO findById(long id) {
		Optional<Patient> searchResult = patientRepository.findById(id);
		
		Patient patient = searchResult.get();
		
		return PatientDTO.from(patient);
	}
	
	public List<PatientDTO> findAll() {
		List<Patient> patients = patientRepository.findAll();
		
		return patients.stream()
				.map(PatientDTO::from)
				.collect(Collectors.toList());
	}
	
	public PatientDTO insert(PatientDTO patientToInsert) {
		// TODO: validate that it looks good here?
		
		// TODO: validate that it's not a duplicate here?
		
		Patient insertedPatient = patientRepository
				.save(patientToInsert.toEntityData());
		
		return PatientDTO.from(insertedPatient);
	}
	
	public PatientDTO update(PatientDTO patientToUpdate) {
		// TODO: validate that it looks good here?
		
		// TODO: validate that it exists in the DB already?
		
		// FIXME: update what caregivers it has here, too!
		
		Patient updatedPatient = patientRepository
				.save(patientToUpdate.toEntityData());
		
		return PatientDTO.from(updatedPatient);
	}
	
	public void deleteById(long medicationId) {
		patientRepository.deleteById(medicationId);
	}

}
