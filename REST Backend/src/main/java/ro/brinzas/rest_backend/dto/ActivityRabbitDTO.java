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
public class ActivityRabbitDTO {
	
	// TODO: we're not sending the activity id anywhere!
	@JsonAlias("patient_id")
	private long patientId;
	private String name;
	private String start;
	private String end;
	
	public static ActivityRabbitDTO from(Activity activity) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return builder()
				.name(activity.getName())
				.start(activity.getStart().format(formatter))
				.end(activity.getEnd().format(formatter))
				.patientId(activity.getPatient().getId())
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
				.name(name)
				.start(LocalDateTime.parse(start, formatter))
				.end(LocalDateTime.parse(end, formatter))
				.abnormal(false)
				.justified(false)
				.build();
	}
}
