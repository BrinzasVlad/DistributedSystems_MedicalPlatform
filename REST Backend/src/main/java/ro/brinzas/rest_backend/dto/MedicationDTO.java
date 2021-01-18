package ro.brinzas.rest_backend.dto;

import ro.brinzas.rest_backend.entities.Medication;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationDTO {
	private final long id;
	private final String name;
	private final String dosage;
	private final String sideEffects;
	
	public static MedicationDTO from(Medication medication) {
		return builder()
				.id(medication.getId())
				.name(medication.getName())
				.dosage(medication.getDosage())
				.sideEffects(medication.getSideEffects())
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
	public Medication toEntityData() {
		return Medication.builder()
				.id(id)
				.name(name)
				.dosage(dosage)
				.sideEffects(sideEffects)
				.build();
	}
}
