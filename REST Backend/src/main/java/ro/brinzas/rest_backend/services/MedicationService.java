package ro.brinzas.rest_backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.brinzas.rest_backend.dto.MedicationDTO;
import ro.brinzas.rest_backend.entities.Medication;
import ro.brinzas.rest_backend.repositories.MedicationRepository;

@Service
public class MedicationService {
	
	private final MedicationRepository medicationRepository;
	
	@Autowired
	private MedicationService(MedicationRepository medicationRepository) {
		this.medicationRepository = medicationRepository;
	}
	
	public MedicationDTO findById(long id) {
		Optional<Medication> searchResult = medicationRepository.findById(id);
		
		Medication medication = searchResult.get();
		
		return MedicationDTO.from(medication);
	}
	
	public List<MedicationDTO> findAll() {
		List<Medication> medications = medicationRepository.findAll();
		
		return medications.stream()
				.map(MedicationDTO::from)
				.collect(Collectors.toList());
	}
	
	public MedicationDTO insert(MedicationDTO medicationToInsert) {
		// TODO: validate that it looks good here?
		
		// TODO: validate that it's not a duplicate here?
		
		Medication insertedMedication = medicationRepository
				.save(medicationToInsert.toEntityData());
		
		return MedicationDTO.from(insertedMedication);
	}
	
	public MedicationDTO update(MedicationDTO medicationToUpdate) {
		// TODO: validate that it looks good here?
		
		// TODO: validate that it exists in the DB already?
		
		Medication updatedMedication = medicationRepository
				.save(medicationToUpdate.toEntityData());
		
		return MedicationDTO.from(updatedMedication);
	}
	
	public void deleteById(long medicationId) {
		medicationRepository.deleteById(medicationId);
	}
}
