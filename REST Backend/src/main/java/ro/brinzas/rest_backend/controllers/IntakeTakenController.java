package ro.brinzas.rest_backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.brinzas.rest_backend.soap_artifacts.SoapIntakeTakenService;
import ro.brinzas.rest_backend.soap_artifacts.SoapIntakeTakenServiceImplService;
import ro.brinzas.rest_backend.dto.IntakeTakenSimpleDTO;

@RestController
@CrossOrigin
@RequestMapping("/patient/{id}/taken") // FIXME: refactor to refer to a constant value somewhere, not /patient/{id}
public class IntakeTakenController {
	
	@GetMapping
	private List<IntakeTakenSimpleDTO> getPatientMedicationTaken(@PathVariable("id") long patientId) {
		List<IntakeTakenSimpleDTO> medicationsTaken = new ArrayList<IntakeTakenSimpleDTO>();

		SoapIntakeTakenServiceImplService service = new SoapIntakeTakenServiceImplService();
		SoapIntakeTakenService proxy = service.getSoapIntakeTakenServiceImplPort();

		proxy.getAllIntakeTakenForPatient(patientId).forEach(intakeTaken -> medicationsTaken.add(
					IntakeTakenSimpleDTO.builder()
						.id(intakeTaken.getId())
						.patientId(intakeTaken.getPatientId())
						.medicationName(intakeTaken.getMedicationName())
						.intervalStart(intakeTaken.getIntervalStart())
						.intervalEnd(intakeTaken.getIntervalEnd())
						.date(intakeTaken.getDate())
						.taken(intakeTaken.isTaken())
						.build())
				);
		
		return medicationsTaken;
	}

}
