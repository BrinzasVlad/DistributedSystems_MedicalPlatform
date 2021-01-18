package ro.brinzas.patient_sensor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRabbitDTO {
	
	private long patient_id;
	private String name;
	private String start;
	private String end;
}
