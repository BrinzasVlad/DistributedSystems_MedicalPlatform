package ro.brinzas.rest_backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.brinzas.rest_backend.dto.CaregiverDTO;
import ro.brinzas.rest_backend.dto.CaregiverNoPatientsDTO;
import ro.brinzas.rest_backend.entities.Caregiver;
import ro.brinzas.rest_backend.repositories.CaregiverRepository;
import ro.brinzas.rest_backend.repositories.PatientRepository;

@Service
public class CaregiverService {
	
	private final CaregiverRepository caregiverRepository;
	private final PatientRepository patientRepository;
	
	@Autowired
	public CaregiverService(CaregiverRepository caregiverRepository,
							PatientRepository patientRepository) {
		this.caregiverRepository = caregiverRepository;
		this.patientRepository = patientRepository;
	}

	@Transactional(readOnly = true)
	public CaregiverDTO findById(long id) {
		Optional<Caregiver> searchResult = caregiverRepository.findById(id);
		
		Caregiver caregiver = searchResult.get();
		
		return CaregiverDTO.from(caregiver);
	}

	@Transactional(readOnly = true)
	public List<CaregiverDTO> findAll() {
		List<Caregiver> caregivers = caregiverRepository.findAll();
		
		return caregivers.stream()
				.map(CaregiverDTO::from)
				.collect(Collectors.toList());
	}
	
	public List<CaregiverNoPatientsDTO> findAllNoPatients() {
		List<Caregiver> caregivers = caregiverRepository.findAll();
		
		return caregivers.stream()
				.map(CaregiverNoPatientsDTO::from)
				.collect(Collectors.toList());
	}
	
	public CaregiverDTO insert(CaregiverDTO caregiverToInsert) {
		// TODO: validate that it looks good here?
		
		// TODO: validate that it's not a duplicate here?
		
		Caregiver insertedCaregiver = caregiverRepository
				.save(caregiverToInsert.toEntityData());
		
		return CaregiverDTO.from(insertedCaregiver);
	}
	
	public CaregiverDTO update(CaregiverDTO caregiverToUpdate) {
		// TODO: validate that it looks good here?
		
		// TODO: validate that it exists in the DB already?
		
		Caregiver entityObtained = caregiverToUpdate.toEntityData();
		caregiverToUpdate.getPatients().forEach(patient -> {
			entityObtained.getPatients().add(
					patientRepository.getOne(patient.getId())
			);
		});
		Caregiver updatedCaregiver = caregiverRepository.save(entityObtained);
		
		return CaregiverDTO.from(updatedCaregiver);
	}
	
	public void deleteById(long medicationId) {
		caregiverRepository.deleteById(medicationId);
	}

}
