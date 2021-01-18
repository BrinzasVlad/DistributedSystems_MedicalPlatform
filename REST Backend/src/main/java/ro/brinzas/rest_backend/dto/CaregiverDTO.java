package ro.brinzas.rest_backend.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import ro.brinzas.rest_backend.entities.Caregiver;
import ro.brinzas.rest_backend.entities.Patient;
import ro.brinzas.rest_backend.entities.enums.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class CaregiverDTO {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	
	private final long id;
	private final String name;
	private final String birthDate; // FIXME: switch to actually using TypeScipt dates (fix the JSON conversion)
//	private final LocalDate birthDate;
	private final String gender;
	private final String address;
	@Singular private final List<PatientDTO> patients;
	
	public static CaregiverDTO from(Caregiver caregiver) {
		return builder()
				.id(caregiver.getId())
				.name(caregiver.getName())
				.birthDate(formatter.format(caregiver.getBirthDate()))
//				.birthDate(patient.getBirthDate())
				.gender(null == caregiver.getGender() ? null : caregiver.getGender().toString())
				.address(caregiver.getAddress())
				.patients(caregiver.getPatients().stream().map(PatientDTO::from).collect(Collectors.toList()))
				.build();
	}
	
	/**
	 * Converts this DTO to its corresponding entity.
	 * <br>
	 * This conversion will NOT set up any relationships to other entities!
	 * (This is because doing so would require database calls in many instances.)
	 * <br>
	 * Thus, entity relationships must be added manually - see
	 * {@link ro.brinzas.rest_backend.services.CaregiverService#update(CaregiverDTO)} for
	 * an example of how this is handled. (Use getOne() to minimize needed SELECT operations)
	 */
	public Caregiver toEntityData() {
		return Caregiver.builder()
				.id(id)
				.name(name)
				.birthDate(LocalDate.parse(birthDate, formatter))
//				.birthDate(birthDate)
				.gender(null == gender ? null : Gender.valueOf(gender))
				.address(address)
				// TODO: should we just to-entity the list of patients here and be done with it?
//				.patients(null == patients ? new HashSet<Patient>() :
//						new HashSet<Patient>(patients.stream()
//						.map(p -> p.toEntityData())
//						.collect(Collectors.toList())))
				.patients(new HashSet<Patient>())
				.build();
	}
}
