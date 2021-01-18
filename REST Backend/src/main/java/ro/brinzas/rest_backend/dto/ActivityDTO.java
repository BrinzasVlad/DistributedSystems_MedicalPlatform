package ro.brinzas.rest_backend.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonAlias;

import ro.brinzas.rest_backend.entities.Activity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
	
	private long id;
	@JsonAlias("patient_id")
	private long patientId;
	private String name;
	private String start;
	private String end;
	private boolean abnormal;
	private boolean justified;
	private String recommendation;
	
	public static ActivityDTO from(Activity activity) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return builder()
				.id(activity.getId())
				.name(activity.getName())
				.start(activity.getStart().format(formatter))
				.end(activity.getEnd().format(formatter))
				.patientId(activity.getPatient().getId())
				.abnormal(activity.isAbnormal())
				.justified(activity.isJustified())
				.recommendation(activity.getRecommendation())
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
	public Activity toEntityData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return Activity.builder()
				.id(id)
				.name(name)
				.start(LocalDateTime.parse(start, formatter))
				.end(LocalDateTime.parse(end, formatter))
				.abnormal(abnormal)
				.justified(justified)
				.recommendation(recommendation)
				.build();
	}
}
