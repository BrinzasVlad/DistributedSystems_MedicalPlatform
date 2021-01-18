package ro.brinzas.rest_backend.dto;

import java.time.LocalTime;

import ro.brinzas.rest_backend.entities.IntakeInterval;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntakeIntervalDTO {
	private final long id;
	private final String startTime;
	private final String endTime;
	private final MedicationDTO medication;
	
	public static IntakeIntervalDTO from(IntakeInterval intakeInterval) {
		return builder()
				.id(intakeInterval.getId())
				.startTime(intakeInterval.getStartTime().toString())
				.endTime(intakeInterval.getEndTime().toString())
				.medication(MedicationDTO.from(intakeInterval.getMedication()))
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
	public IntakeInterval toEntityData() {
		return IntakeInterval.builder()
				.id(id)
				.startTime(LocalTime.parse(startTime))
				.endTime(LocalTime.parse(endTime))
				.build();
	}

}
