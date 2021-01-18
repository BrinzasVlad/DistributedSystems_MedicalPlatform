package ro.brinzas.soap_backend.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;

/**
 * Stub class meant to enable JPA queries.
 * Do NOT actually use - it only serves to allow us to dig for connections
 */
@Getter
@Entity
@Table(name = "intake_interval")
public class StubIntakeInterval {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;

	@NonNull
	@Column(name = "start_time", nullable = false)
	private LocalTime startTime;
	
	@NonNull
	@Column(name = "end_time", nullable = false)
	private LocalTime endTime;
	
	@NonNull
	@ManyToOne // No cascade
	@JoinColumn(name = "medication_id", nullable = false)
	private StubMedication medication;

	@NonNull
	@ManyToOne // No cascade
	@JoinColumn(name = "plan_id", nullable = false)
	private StubMedicationPlan plan;

}
