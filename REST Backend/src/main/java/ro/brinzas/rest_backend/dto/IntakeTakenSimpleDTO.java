package ro.brinzas.rest_backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntakeTakenSimpleDTO {
	
	private long id;
	
	@JsonAlias("patient_id")
	private long patientId;
	
	@JsonAlias("medication_name")
	private String medicationName;
	
	@JsonAlias("interval_start")
	private String intervalStart;
	
	@JsonAlias("interval_end")
	private String intervalEnd;
	
	private String date;
	
	private boolean taken;
}
