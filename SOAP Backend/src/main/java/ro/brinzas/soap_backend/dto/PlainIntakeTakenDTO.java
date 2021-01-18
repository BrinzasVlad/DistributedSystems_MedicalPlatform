package ro.brinzas.soap_backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ro.brinzas.soap_backend.adapters.LocalDateAdapter;
import ro.brinzas.soap_backend.adapters.LocalTimeAdapter;
import ro.brinzas.soap_backend.entities.PlainIntakeTaken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
// XML accessor FIELD needed because else the annotation on the field and the root element
// on the constructor ends up generating some fields twice

public class PlainIntakeTakenDTO {
	private long id;
	private long patientId;
	private String medicationName;
	@XmlJavaTypeAdapter(LocalTimeAdapter.class)
	private LocalTime intervalStart;
	@XmlJavaTypeAdapter(LocalTimeAdapter.class)
	private LocalTime intervalEnd;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate date;
	private boolean taken;
	
	public static PlainIntakeTakenDTO from(PlainIntakeTaken intakeTaken) {
		return builder()
				.id(intakeTaken.getId())
				.patientId(intakeTaken.getIntake().getPlan().getPatientId())
				.medicationName(intakeTaken.getIntake().getMedication().getName())
				.intervalStart(intakeTaken.getIntake().getStartTime())
				.intervalEnd(intakeTaken.getIntake().getEndTime())
				.date(intakeTaken.getDate())
				.taken(intakeTaken.isTaken())
				.build();
	}
}
