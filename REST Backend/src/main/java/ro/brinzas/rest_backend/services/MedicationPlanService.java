package ro.brinzas.rest_backend.services;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.brinzas.rest_backend.dto.MedicationPlanNoPatientDTO;
import ro.brinzas.rest_backend.entities.MedicationPlan;
import ro.brinzas.rest_backend.repositories.MedicationPlanRepository;

@Service
public class MedicationPlanService {
	
	private final MedicationPlanRepository medicationPlanRepository;

	@Autowired
	public MedicationPlanService(MedicationPlanRepository medicationPlanRepository) {
		this.medicationPlanRepository = medicationPlanRepository;
	}
	
	public MedicationPlanNoPatientDTO findByIdNoPatient(long id) {
		Optional<MedicationPlan> searchResult = medicationPlanRepository.findById(id);
		
		MedicationPlan medicationPlan = searchResult.get();
		
		return MedicationPlanNoPatientDTO.from(medicationPlan);
	}
	
	public List<MedicationPlanNoPatientDTO> findAllNoPatient() {
		List<MedicationPlan> medicationPlans = medicationPlanRepository.findAll();
		
		return medicationPlans.stream()
				.map(MedicationPlanNoPatientDTO::from)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<MedicationPlanNoPatientDTO> findAllNoPatientByPatientIdForToday(long patientId) {
		return findAllNoPatientByPatientIdForDate(patientId, LocalDate.now(Clock.systemUTC()));
	}

	@Transactional(readOnly = true)
	public List<MedicationPlanNoPatientDTO> findAllNoPatientByPatientIdForDate(long patientId, LocalDate date) {
		List<MedicationPlan> medicationPlans = medicationPlanRepository
				.findByPatientIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(patientId, date, date);
		
		return medicationPlans.stream()
				.map(MedicationPlanNoPatientDTO::from)
				.collect(Collectors.toList());
	}
	
}