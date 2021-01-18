package ro.brinzas.rest_backend.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ro.brinzas.rest_backend.entities.Patient;
import ro.brinzas.rest_backend.entities.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientDTO {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	
	private final long id;
	private final String name;
	private final String birthDate; // FIXME: switch to actually using TypeScipt dates (fix the JSON conversion)
//	private final LocalDate birthDate;
	private final String gender;
	private final String address;
	
	public static PatientDTO from(Patient patient) {
		return builder()
				.id(patient.getId())
				.name(patient.getName())
				.birthDate(formatter.format(patient.getBirthDate()))
//				.birthDate(patient.getBirthDate())
				.gender(null == patient.getGender() ? null : patient.getGender().toString())
				.address(patient.getAddress())
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
	public Patient toEntityData() {
		return Patient.builder()
				.id(id)
				.name(name)
				.birthDate(LocalDate.parse(birthDate, formatter))
//				.birthDate(birthDate)
				.gender(null == gender ? null : Gender.valueOf(gender))
				.address(address)
				.build();
	}
}
