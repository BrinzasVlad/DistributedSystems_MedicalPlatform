package ro.brinzas.rest_backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ro.brinzas.rest_backend.entities.MedicationPlan;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class MedicationPlanNoPatientDTO {
	private final long id;
	private final LocalDate startDate;
	private final LocalDate endDate;
	@Singular private final List<IntakeIntervalDTO> intakeIntervals;
	
	public static MedicationPlanNoPatientDTO from(MedicationPlan medicationPlan) {
		return builder()
				.id(medicationPlan.getId())
				.startDate(medicationPlan.getStartDate())
				.endDate(medicationPlan.getEndDate())
				.intakeIntervals(
						medicationPlan.getIntakeIntervals().stream()
						.map(IntakeIntervalDTO::from)
						.collect(Collectors.toList()))
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
	public MedicationPlan toEntityData() {
		return MedicationPlan.builder()
				.id(id)
				.startDate(startDate)
				.endDate(endDate)
				.build();
	}
	
}
